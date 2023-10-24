package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessInitDTOResponse;
import com.example.birdReproductionManagement.entity.BirdType;

public class BirdTypeMapper {
    public static BirdType mapToBirdType(BirdTypeDTO birdTypeDto){
        return BirdType.builder()
//                .id(birdTypeDto.getId())
                .name(birdTypeDto.getName())
                .incubate(birdTypeDto.getIncubate())
                .chick(birdTypeDto.getChick())
                .swingBranch(birdTypeDto.getSwingBranch())
                .description(birdTypeDto.getDescription())
                .lifeExpectancy(birdTypeDto.getLifeExpectancy())
//                .birdList(birdTypeDto.getBirdList().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))
//                .meals(birdTypeDto.getMeals().stream().map(MealMapper::mapToMeal).collect(Collectors.toList()))
                .build();
    }

    public static BirdTypeDTO mapToBirdTypeDto(BirdType birdType){
        return BirdTypeDTO.builder()
                .typeId(String.valueOf(birdType.getId()))
                .name(birdType.getName())
                .incubate(birdType.getIncubate())
                .chick(birdType.getChick())
                .swingBranch(birdType.getSwingBranch())
                .description(birdType.getDescription())
                .lifeExpectancy(birdType.getLifeExpectancy())
//                .birdList(birdType.getBirdList().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .meals(birdType.getMeals().stream().map(MealMapper::mapToMealDto).collect(Collectors.toList()))
                .build();
    }

    public static BirdType4CageDetailDTOResponse map2BirdtypeDTO(BirdType birdType){
        return BirdType4CageDetailDTOResponse.builder()
                .typeId(String.valueOf(birdType.getId()))
                .name(birdType.getName())
                .incubate(birdType.getIncubate())
                .chick(birdType.getChick())
                .swingBranch(birdType.getSwingBranch())
                .lifeExpectancy(birdType.getLifeExpectancy())
                .build();
    }
    public static BirdType4ProcessDTOResponse map2BirdType4ProcessDTO(BirdType birdType){
        return BirdType4ProcessDTOResponse.builder()
                .typeId(birdType.getId() + "")
                .name(birdType.getName())
                .incubate(birdType.getIncubate())
                .chick(birdType.getChick())
                .swingBranch(birdType.getSwingBranch())
                .lifeExpectancy(birdType.getLifeExpectancy())
                .build();
    }
    public static BirdType4ProcessInitDTOResponse map2BirdType4ProcessInitDTO(BirdType birdType){
        return BirdType4ProcessInitDTOResponse.builder()
                .typeId(birdType.getId() + "")
                .name(birdType.getName())
                .incubate(birdType.getIncubate())
                .chick(birdType.getChick())
                .swingBranch(birdType.getSwingBranch())
                .lifeExpectancy(birdType.getLifeExpectancy())
                .build();
    }
}