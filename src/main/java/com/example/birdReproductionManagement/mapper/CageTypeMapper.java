package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.CageTypeDto;
import com.example.birdReproductionManagement.model.CageType;

public class CageTypeMapper {
    public static CageType mapToCageType(CageTypeDto cageTypeDto){
        return CageType.builder()
                .id(cageTypeDto.getId())
                .name(cageTypeDto.getName())
                .size(cageTypeDto.getSize())
                .description(cageTypeDto.getDescription())
//                .cageList(cageTypeDto.getCageList().stream().map(CageMapper::mapToCage).collect(Collectors.toList()))
                .build();
    }

    public static CageTypeDto mapToCageTypeDto(CageType cageType1){
        return CageTypeDto.builder()
                .id(cageType1.getId())
                .name(cageType1.getName())
                .size(cageType1.getSize())
                .description(cageType1.getDescription())
//                .cageList(cageType.getCageList().stream().map(CageMapper::mapToCageDto).collect(Collectors.toList()))
                .build();
    }
}
