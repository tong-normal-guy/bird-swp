package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
import com.example.birdReproductionManagement.entity.Bird;

import com.example.birdReproductionManagement.entity.Sex;


public class BirdMapper {
    public static Bird mapToBird(BirdDto birdDto){
        return Bird.builder()
//                .id(Long.valueOf(birdDto.getId()))
                .sex(Sex.valueOf(birdDto.getSex()))
                .hatchDate(birdDto.getHatchDate())
                .ageRange(birdDto.getAgeRange())
                .mutation(birdDto.getMutation())
                .mutationRate(birdDto.getMutationRate())
                .isAlive(birdDto.getIsAlive())
                .image(birdDto.getImage())
                .featherColor(birdDto.getFeatherColor())
                .weight(birdDto.getWeight())
//                .birdType(BirdTypeMapper.mapToBirdType(birdDto.getBirdType()))
//                .birdListOfFather(birdDto.getBirdListOfFather().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))

//                .birdListOfMother(birdDto.getBirdListOfMother().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))

//                .cage(CageMapper.mapToCage(birdDto.getCage()))
//                .birdCageHistories(birdDto.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistory).collect(Collectors.toList()))
//                .birdReproductions(birdDto.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproduction).collect(Collectors.toList()))
                .build();
    }

    public static BirdDto mapToBirdDto(Bird bird){
        return BirdDto.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(bird.getSex().name())
                .hatchDate(bird.getHatchDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .isAlive(bird.getIsAlive())
                .image(bird.getImage())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .birdTypeName(bird.getBirdType().getName())
                .cageId(String.valueOf(bird.getCage().getId()))
                .cage(CageMapper.mapToCageDto(bird.getCage()))
                .birdType(BirdTypeMapper.mapToBirdTypeDto(bird.getBirdType()))
//                .birdListOfFather(bird.getBirdListOfFather().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .birdListOfMother(bird.getBirdListOfMother().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .cage(CageMapper.mapToCageDto(bird.getCage()))
//                .birdCageHistories(bird.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistoryDto).collect(Collectors.toList()))
//                .birdReproductions(bird.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList()))
                .build();
    }

    public static Bird4CageDetailDTOResponse map2Birdd4CageDetailDTO (Bird bird){
        return Bird4CageDetailDTOResponse.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(String.valueOf(bird.getSex()))
                .birdType(BirdTypeMapper.map2BirdtypeDTO(bird.getBirdType()))
                .mutationRate(bird.getMutationRate())
                .weight(bird.getWeight())
                .image(bird.getImage())
                .build();
    }
    public static Bird4ProcessDTOResponse map2Bird4ProcessDTO(Bird bird){
        return Bird4ProcessDTOResponse.builder()
                .birdId(bird.getId() + "")
                .sex(bird.getSex() + "")
                .hatchDate(bird.getHatchDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .isAlive(bird.getIsAlive())
                .image(bird.getImage())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .build();
    }
}
