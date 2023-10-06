package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.model.BirdReproduction;

public class BirdReproductionMapper {
    public static BirdReproduction mapToBirdReproduction (BirdReproductionDto birdReproductionDto){
        return BirdReproduction.builder()
                .id(birdReproductionDto.getId())
                .bird(birdReproductionDto.getBird())
                .reproductionProcess(birdReproductionDto.getReproductionProcess())
                .isChild(birdReproductionDto.getIsChild())
                .build();
    }

    public static BirdReproductionDto mapToBirdReproductionDto (BirdReproduction birdReproduction){
        return BirdReproductionDto.builder()
                .id(birdReproduction.getId())
                .bird(birdReproduction.getBird())
                .reproductionProcess(birdReproduction.getReproductionProcess())
                .isChild(birdReproduction.getIsChild())
                .build();
    }
}
