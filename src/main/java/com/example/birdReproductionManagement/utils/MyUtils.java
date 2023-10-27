package com.example.birdReproductionManagement.utils;
import com.example.birdReproductionManagement.dto.BirdParentDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDetailReponseDTO;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class MyUtils {
    public static List<Bird> findSortedBirdsByCount(Sex sex, Long birdTypeId, BirdRepository birdRepository) {
        List<Bird> birds = birdRepository.findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(sex, birdTypeId);

        if (sex.equals(Sex.MALE)){
            Collections.sort(birds, (bird1, bird2) -> {
                long fatherId1 = bird1.getId();
                long fatherId2 = bird2.getId();

                long count1 = birds.stream().filter(b -> b.getFatherId() == fatherId1).count();
                long count2 = birds.stream().filter(b -> b.getFatherId() == fatherId2).count();
                return Long.compare(count2, count1); // Sắp xếp từ lớn đến nhỏ
            });
        }else {
            Collections.sort(birds, (bird1, bird2) -> {
                long motherId1 = bird1.getId();
                long motherId2 = bird2.getId();
                long count1 = birds.stream().filter(b -> b.getMotherId() == motherId1).count();
                long count2 = birds.stream().filter(b -> b.getMotherId() == motherId2).count();
                return Long.compare(count2, count1); // Sắp xếp từ lớn đến nhỏ
            });
        }
        // Sắp xếp danh sách theo số lượng xuất hiện của fatherId
        return birds;
    }

    public static List<Bird> findSortedBirdBySuperReproduct(Sex sex, Long birdTypeId, BirdRepository birdRepository){
        List<Bird> birdParentList = null;
        List<BirdParentDTO> birdParentAndChildsList = new ArrayList<>();

        if (sex.equals(Sex.MALE)){
            birdParentList = birdRepository.findParents(Sex.MALE,birdTypeId);
            for (Bird birdFather:birdParentList
            ) {
                BirdParentDTO birdParentDTO = new BirdParentDTO();
                List<Bird> birdChildList = birdRepository.findAllByFatherId(birdFather.getFatherId() ) ;

                birdParentDTO.setBirdParent(birdFather);
                if (birdChildList != null && !birdChildList.isEmpty()){
                    birdParentDTO.setSize(birdChildList.size());
                }
                birdParentAndChildsList.add(birdParentDTO);
            }
        }else {
            birdParentList = birdRepository.findParents(Sex.FEMALE,birdTypeId);
            for (Bird birdMother:birdParentList
            ) {
                BirdParentDTO birdParentDTO = new BirdParentDTO();
                List<Bird> birdChildList = birdRepository.findAllByMotherId(birdMother.getMotherId() ) ;

                birdParentDTO.setBirdParent(birdMother);
                if (birdChildList != null && !birdChildList.isEmpty()){
                    birdParentDTO.setSize(birdChildList.size());
                }
                birdParentAndChildsList.add(birdParentDTO);
            }
        }

        return birdParentAndChildsList.stream()
                .sorted(Comparator.comparingInt(BirdParentDTO::getSize).reversed())
                .map(BirdParentDTO::getBirdParent)
                .collect(Collectors.toList());
    }

    public static BirdRe4CageDetailDTOResponse expDateByLaidDate4BirdReproduct (BirdRe4CageDetailDTOResponse birdReproductionDto, BirdType birdType ){
        if (birdReproductionDto.getEggLaidDate() != null && birdReproductionDto != null){
            // Tạo một đối tượng Date
            Date currentDate = new Date();


            // Số ngày cần thêm vào (dưới dạng long)
            long daysToAdd = birdType.getIncubate();

            // Chuyển long thành int (nếu cần)
            int daysToAddAsInt = (int) daysToAdd;

            // Sử dụng Calendar để thực hiện phép tính
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);  // Đặt thời điểm ban đầu

            // Thêm số ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, daysToAddAsInt);
            Date expHatched = calendar.getTime();

            birdReproductionDto.setExpEggHatchDate(expHatched);

            // Số ngày cần thêm vào (dưới dạng long)
             daysToAdd = birdType.getChick();

            // Chuyển long thành int (nếu cần)
            daysToAddAsInt = (int) daysToAdd;
            calendar.add(Calendar.DAY_OF_MONTH, daysToAddAsInt);

            Date expChick = calendar.getTime();
            birdReproductionDto.setExpSwingBranch(expChick);

            // Số ngày cần thêm vào (dưới dạng long)
            daysToAdd = birdType.getSwingBranch();

            // Chuyển long thành int (nếu cần)
            daysToAddAsInt = (int) daysToAdd;
            calendar.add(Calendar.DAY_OF_MONTH, daysToAddAsInt);

            Date expSwing = calendar.getTime();
            birdReproductionDto.setExpAdultBirdDate(expSwing);
        }
        return birdReproductionDto;
    }

    public static BirdReproductionDTO expDateByLaidDate4BirdReproduct (BirdReproductionDTO birdReproductionDto, BirdType birdType ){
        if (birdReproductionDto.getEggLaidDate() != null && birdReproductionDto != null){
            // Tạo một đối tượng Date
            Date currentDate = new Date();

            // Số ngày cần thêm vào (dưới dạng long)
            long daysToAdd = birdType.getIncubate();

            // Chuyển long thành int (nếu cần)
            int daysToAddAsInt = (int) daysToAdd;

            // Sử dụng Calendar để thực hiện phép tính
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);  // Đặt thời điểm ban đầu

            // Thêm số ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, daysToAddAsInt);
            Date expHatched = calendar.getTime();

            birdReproductionDto.setExpEggHatchDate(expHatched);

            // Số ngày cần thêm vào (dưới dạng long)
            daysToAdd = birdType.getChick();

            // Chuyển long thành int (nếu cần)
            daysToAddAsInt = (int) daysToAdd;
            calendar.add(Calendar.DAY_OF_MONTH, daysToAddAsInt);

            Date expChick = calendar.getTime();
            birdReproductionDto.setExpSwingBranch(expChick);

            // Số ngày cần thêm vào (dưới dạng long)
            daysToAdd = birdType.getSwingBranch();

            // Chuyển long thành int (nếu cần)
            daysToAddAsInt = (int) daysToAdd;
            calendar.add(Calendar.DAY_OF_MONTH, daysToAddAsInt);

            Date expSwing = calendar.getTime();
            birdReproductionDto.setExpAdultBirdDate(expSwing);
        }
        return birdReproductionDto;
    }

    public static Date calculateDate(Date date, long number){
        int dayToAdd = (int) number;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, dayToAdd);
        return calendar.getTime();
    }

}
