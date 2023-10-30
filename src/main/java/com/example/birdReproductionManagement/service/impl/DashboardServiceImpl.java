package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.DashboardResponse.DashboardDTOResponse;
import com.example.birdReproductionManagement.dto.DashboardResponse.EggInWeekDTOResponse;
import com.example.birdReproductionManagement.dto.DashboardResponse.EggPerDayDTOResponse;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.repository.UserRepository;
import com.example.birdReproductionManagement.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final BirdRepository birdRepository;
    private final ReproductionProcessRepository processRepository;
    private final UserRepository userRepository;
    private final BirdReproductionRepository reproductionRepository;
    @Override
    public DashboardDTOResponse getDashboard() {
        List<Bird> birdList = birdRepository.findByIsAliveTrue();
        int totalBird = birdList.size();
        int totalMutationN = 0;
        Integer totalProcess = processRepository.countAllByIsDoneFalse();
        Integer totalUser = userRepository.countAllByRole(Role.STAFF);
        Integer totalEgg = reproductionRepository.countAllByReproductionRoleAndIsFailIsFalse(ReproductionRole.EGG);
        List<Bird> truongThanhBirds = null;
        List<Bird> chuyenBirds = null;
        List<Bird> nonBirds = null;
        List<Bird> top5Birds = null;
        List<BirdDTO> top5BirdDTOs = null;
        if (!birdList.isEmpty()){
            // Phân loại danh sách chim theo ageRange
            truongThanhBirds = birdList.stream()
                    .filter(bird -> bird.getAgeRange().contains("Trưởng thành"))
                    .collect(Collectors.toList());

            chuyenBirds = birdList.stream()
                    .filter(bird -> bird.getAgeRange().equals("Chuyển"))
                    .collect(Collectors.toList());

            nonBirds = birdList.stream()
                    .filter(bird -> bird.getAgeRange().equals("Non"))
                    .collect(Collectors.toList());

            totalMutationN = birdList.stream().filter(bird -> bird.getMutation()!=null).collect(Collectors.toList()).size();

            top5Birds = birdList.stream()
                    .sorted(Comparator.comparing(Bird::getHatchDate))
                    .limit(5)
                    .collect(Collectors.toList());

            top5BirdDTOs =top5Birds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
        }

        return DashboardDTOResponse.builder()
                .totalBird(totalBird)
                .totalProcess(totalProcess)
                .totalUser(totalUser)
                .totalMutation(totalMutationN)
                .totalEgg(totalEgg)
                .top5Birds(top5BirdDTOs)
                .totalAdult(truongThanhBirds.size())
                .totalSwingbranch(chuyenBirds.size())
                .totalBaby(nonBirds.size())
                .build();
    }

    public EggPerDayDTOResponse eggaDayDTO(Date date){
        Integer inDev = reproductionRepository.countByEggRoleAndInDevAndDate(date);
        Integer broken = reproductionRepository.countByEggRoleAndBrokenAndDate(date);

        return EggPerDayDTOResponse.builder()
                .eggLaidDate(date)
                .totalSucessEgg(inDev)
                .totalFailEgg(broken)
                .build();
    }
    public EggInWeekDTOResponse eggaWeekDTO(){
        List<EggPerDayDTOResponse> eggaDays = new ArrayList<>();
        for (int i = 0; i < 7; i--){

        }
        return EggInWeekDTOResponse.builder().build();
    }
}
