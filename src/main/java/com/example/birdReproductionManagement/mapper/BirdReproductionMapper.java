package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionRole;

public class BirdReproductionMapper {
    public static BirdReproduction mapToBirdReproduction (BirdReproductionDto birdReproductionDto){
        return BirdReproduction.builder()
                .id(birdReproductionDto.getId())
                .bird(BirdMapper.mapToBird(birdReproductionDto.getBird()))
                .eggLaidDate(birdReproductionDto.getEggLaidDate())
                .actEggHatchDate(birdReproductionDto.getActEggHatchDate())
                .actSwingBranch(birdReproductionDto.getActSwingBranch())
                .actAdultBirdDate(birdReproductionDto.getActAdultBirdDate())
                .eggType(birdReproductionDto.getEggType())
                .eggStatus(birdReproductionDto.getEggStatus())
                .isFail(birdReproductionDto.getIsFail())
                .failDate(birdReproductionDto.getFailDate())
                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcess(birdReproductionDto.getReproductionProcess()))
                .reproductionRole(ReproductionRole.valueOf(birdReproductionDto.getReproductionRole()))
//                .isChild(birdReproductionDto.getIsChild())
                .build();
    }

    public static BirdReproductionDto mapToBirdReproductionDto (BirdReproduction birdReproduction){
        return BirdReproductionDto.builder()
                .id(birdReproduction.getId())
                .bird(BirdMapper.mapToBirdDto(birdReproduction.getBird()))
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .actSwingBranch(birdReproduction.getActSwingBranch())
                .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                .eggType(birdReproduction.getEggType())
                .eggStatus(birdReproduction.getEggStatus())
                .isFail(birdReproduction.getIsFail())
                .failDate(birdReproduction.getFailDate())
                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                .reproductionRole(birdReproduction.getReproductionRole().name())
//                .isChild(birdReproduction.getIsChild())
                .build();
    }
}
