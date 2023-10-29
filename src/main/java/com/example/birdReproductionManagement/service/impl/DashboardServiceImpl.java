package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.DashboardDTOResponse;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.repository.UserRepository;
import com.example.birdReproductionManagement.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final BirdRepository birdRepository;
    private final ReproductionProcessRepository processRepository;
    private final UserRepository userRepository;
    @Override
    public DashboardDTOResponse getDashboard() {
        List<Bird> birdList = birdRepository.findByIsAliveTrue();

        int totalBird = birdList.size();
        int totalMutationN = 0;
        Integer totalProcess = processRepository.countAllByIsDoneFalse();
        Integer totalUser = userRepository.countAllByRole(Role.STAFF);

        // Phân loại danh sách chim theo ageRange
        List<Bird> truongThanhBirds = birdList.stream()
                .filter(bird -> bird.getAgeRange().contains("Trưởng thành"))
                .collect(Collectors.toList());

        List<Bird> chuyenBirds = birdList.stream()
                .filter(bird -> bird.getAgeRange().equals("Chuyển"))
                .collect(Collectors.toList());

        List<Bird> nonBirds = birdList.stream()
                .filter(bird -> bird.getAgeRange().equals("Non"))
                .collect(Collectors.toList());

        totalMutationN = birdList.stream().filter(bird -> !bird.getMutation().isEmpty()).collect(Collectors.toList()).size();

        List<Bird> top5Birds = birdList.stream()
                .sorted(Comparator.comparing(Bird::getHatchDate))
                .limit(5)
                .collect(Collectors.toList());

        List<BirdDTO> top5BirdDTOs =top5Birds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
        return DashboardDTOResponse.builder()
                .totalBird(totalBird)
                .totalProcess(totalProcess)
                .totalUser(totalUser)
                .totalMutation(totalMutationN)
                .top5Birds(top5BirdDTOs)
                .totalAdult(truongThanhBirds.size())
                .totalSwingbranch(chuyenBirds.size())
                .totalBaby(nonBirds.size())
                .build();
    }
}
