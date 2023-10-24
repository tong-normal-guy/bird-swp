package com.example.birdReproductionManagement.utils;

import com.example.birdReproductionManagement.dto.BirdParentDTO;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.entity.Sex;
import com.example.birdReproductionManagement.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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




}
