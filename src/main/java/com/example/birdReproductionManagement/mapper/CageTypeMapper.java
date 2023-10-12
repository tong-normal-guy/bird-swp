package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.CageTypeDto;
import com.example.birdReproductionManagement.model.CageType;

import java.util.stream.Collectors;

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

    public static CageTypeDto mapToCageTypeDto(CageType cageType){
        return CageTypeDto.builder()
                .id(cageType.getId())
                .name(cageType.getName())
                .size(cageType.getSize())
                .description(cageType.getDescription())
//                .cageList(cageType.getCageList().stream().map(CageMapper::mapToCageDto).collect(Collectors.toList()))
                .build();
    }
}
