package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.DashboardResponse.CloseDateReproductionDTOResponse;
import com.example.birdReproductionManagement.dto.DashboardResponse.DashboardDTOResponse;
import com.example.birdReproductionManagement.dto.DashboardResponse.EggInWeekDTOResponse;
import com.example.birdReproductionManagement.dto.DashboardResponse.EggPerDayDTOResponse;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.mapper.ReproductionProcessMapper;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.repository.UserRepository;
import com.example.birdReproductionManagement.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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
        int totalCock = 0;
        int totalHen = 0;
        if (!birdList.isEmpty()){
            // Phân loại danh sách chim theo ageRange
            truongThanhBirds = birdList.stream()
                    .filter(bird -> bird.getAgeRange().equals("Trưởng thành"))
                    .collect(Collectors.toList());

            chuyenBirds = birdList.stream()
                    .filter(bird -> bird.getAgeRange().equals("Chuyền"))
                    .collect(Collectors.toList());

            nonBirds = birdList.stream()
                    .filter(bird -> bird.getAgeRange().equals("Non"))
                    .collect(Collectors.toList());

            totalCock = birdList.stream().filter(bird -> bird.getSex().equals(Sex.MALE)).collect(Collectors.toList()).size();
            totalHen = birdList.stream().filter(bird -> bird.getSex().equals(Sex.FEMALE)).collect(Collectors.toList()).size();

            totalMutationN = birdList.stream().filter(bird -> bird.getMutation()!=null).collect(Collectors.toList()).size();

            top5Birds = birdList.stream()
                    .sorted(Comparator.comparing(Bird::getId).reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            top5BirdDTOs = top5Birds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
        }

        return DashboardDTOResponse.builder()
                .totalBird(totalBird)
                .totalProcess(totalProcess)
                .totalUser(totalUser)
                .totalMutation(totalMutationN)
                .totalEgg(totalEgg)
                .totalCock(totalCock)
                .totalHen(totalHen)
                .top5Birds(top5BirdDTOs)
                .totalAdult(truongThanhBirds.size())
                .totalSwingbranch(chuyenBirds.size())
                .totalBaby(nonBirds.size())
                .totalEggIn7Day(eggaWeekDTO())
                .bird_reproduction(closeDateReDTO())
                .build();
    }

    public EggPerDayDTOResponse eggaDayCaculate(Date date){
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
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i > -7; i--){
// nếu trừ mà ra số âm thì sao?
            calendar.setTime(current);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            eggaDays.add(eggaDayCaculate(calendar.getTime()));
        }
        return EggInWeekDTOResponse.builder()
                .perDay(eggaDays)
                .build();
    }
    public List<CloseDateReproductionDTOResponse> closeDateProcessDTO(){
            Date current = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(current);
//            List<CloseDateReproductionDTOResponse> closePress = processRepository.findReproductionProcessesWithMatchingDates(current)
//                    .stream().map(ReproductionProcessMapper::map2CloseDateProcessDTO).collect(Collectors.toList());
            List<CloseDateReproductionDTOResponse> closePin = reproductionRepository.findBirdReproductionsByConditions(calendar.getTime())
                    .stream().map(BirdReproductionMapper::map2CloseDateReproductionDTO).collect(Collectors.toList());
            return closePin;
//            List<ReproductionProcess> processes = processRepository.findAllByIsDoneFalse();
//        // Sắp xếp danh sách processes theo expEggHatchDate của BirdReproduction đầu tiên
//        Collections.sort(processes, (p1, p2) -> {
//            Date expDate1 = getFirstExpDate(p1);
//            Date expDate2 = getFirstExpDate(p2);
//            if(expDate1 == null && expDate2 == null) {
//                return 0;
//            }
//            if(expDate1 == null) {
//                return 1;
//            }
//            if(expDate2 == null) {
//                return -1;
//            }
//            return expDate2.compareTo(expDate1);
//        });
//        Date currentDate = new Date();
//        List<ReproductionProcess> filteredProcesses = processes.stream()
//                .filter(process -> {
//                    if (!process.getBirdReproductions().isEmpty()) {
//                        // Lọc và sắp xếp các BirdReproduction
//                        List<BirdReproduction> filteredBirdReproductions = process.getBirdReproductions().stream()
//                                .filter(birdReproduction -> birdReproduction.getReproductionRole() == ReproductionRole.EGG || birdReproduction.getReproductionRole() == ReproductionRole.CHILD)
//                                .filter(birdReproduction -> birdReproduction.getExpEggHatchDate() != null) // Đảm bảo expEggHatchDate không null
//                                .sorted((br1, br2) -> br1.getExpEggHatchDate().compareTo(br2.getExpEggHatchDate()))
//                                .collect(Collectors.toList());
//                        Date expEggHatchDate = filteredBirdReproductions.get(0).getExpEggHatchDate();
//                        // Lấy ngày trước 1 ngày của expEggHatchDate đầu tiên
//                        if(expEggHatchDate != null) {
//                            expEggHatchDate.setDate(expEggHatchDate.getDate() - 1);
//                        }
//                        return expEggHatchDate != null && currentDate.after(expEggHatchDate);
//                    }
//                    return false;
//                })
//                .collect(Collectors.toList());
//        return filteredProcesses.stream().map(ReproductionProcessMapper::map2CloseDateProcessDTO).collect(Collectors.toList());
    }
    private Date getFirstExpDate(ReproductionProcess process) {
        if(process.getBirdReproductions().isEmpty()) {
            return null;
        }

        Date date = process.getBirdReproductions().get(0).getExpEggHatchDate();

        if(date == null) {
            return date;
        }

        return date;
    }
    private List<CloseDateReproductionDTOResponse> closeDateReDTO(){
        List<CloseDateReproductionDTOResponse> closeDateReproDTOs = new ArrayList<>();
        Bird bird;
        Date current = new Date();
        ReproductionProcess process;
        List<BirdReproduction> reproductions = reproductionRepository.findEggOrChildReproductionsWithIsDoneFalse();
        for (BirdReproduction reproduction: reproductions) {
            Calendar calendar = Calendar.getInstance();
            bird = reproduction.getBird();
            if (bird == null){
                calendar.setTime(reproduction.getExpEggHatchDate());
                calendar.add(Calendar.DAY_OF_MONTH, -2);
                if (current.after(calendar.getTime())) {
                    closeDateReproDTOs.add(closeDate(reproduction, "hatch", reproduction.getExpEggHatchDate()));

                }
            } else {
                if (bird.getSwingBranchDate() == null){
                    calendar.setTime(reproduction.getExpSwingBranchDate());
                    calendar.add(Calendar.DAY_OF_MONTH, -2);
                    if (current.after(calendar.getTime())){
                        closeDateReproDTOs.add(closeDate(reproduction, "swing", reproduction.getExpSwingBranchDate()));
                    }
                } else if (bird.getAdultBirdDate() == null) {
                    calendar.setTime(reproduction.getExpSwingBranchDate());
                    calendar.add(Calendar.DAY_OF_MONTH, -2);
                    if (current.after(calendar.getTime())){
                        closeDateReproDTOs.add(closeDate(reproduction, "adult", reproduction.getExpAdultBirdDate()));

                    }
                }
            }
        }
        return closeDateReproDTOs;
    }
    public CloseDateReproductionDTOResponse closeDate(BirdReproduction reproduction, String desc, Date next){
        return CloseDateReproductionDTOResponse.builder()
                .pairingDate(reproduction.getReproductionProcess().getPairingDate())
                .cageId(reproduction.getReproductionProcess().getCage().getId()+"")
                .reproductionRole(reproduction.getReproductionRole()+"")
                .desc(desc)
                .nextDate(next)
                .build();
    }
}
