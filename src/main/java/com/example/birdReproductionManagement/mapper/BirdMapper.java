package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdDto;
import com.example.birdReproductionManagement.model.Bird;
import com.example.birdReproductionManagement.model.BirdCageHistory;
import com.example.birdReproductionManagement.model.BirdReproduction;

import java.util.stream.Collectors;

public class BirdMapper {
    public static Bird mapToBird(BirdDto birdDto){
        return Bird.builder()
                .id(birdDto.getId())
                .sex(birdDto.getSex())
                .hatchDate(birdDto.getHatchDate())
                .ageRange(birdDto.getAgeRange())
                .mutation(birdDto.getMutation())
                .image(birdDto.getImage())
                .weight(birdDto.getWeight())
                .birdType(birdDto.getBirdType())
                .birdListOfFather(birdDto.getBirdListOfFather().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))
                .father(birdDto.getFather())
                .birdListOfMother(birdDto.getBirdListOfMother().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))
                .mother(birdDto.getMother())
                .cage(birdDto.getCage())
                .birdCageHistories(birdDto.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistory).collect(Collectors.toList()))
                .birdReproductions(birdDto.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproduction).collect(Collectors.toList()))
                .build();
    }

    public static BirdDto mapToBirdDto(Bird bird){
        return BirdDto.builder()
                .id(bird.getId())
                .sex(bird.getSex())
                .hatchDate(bird.getHatchDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .image(bird.getImage())
                .weight(bird.getWeight())
                .birdType(bird.getBirdType())
                .birdListOfFather(bird.getBirdListOfFather().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
                .father(bird.getFather())
                .birdListOfMother(bird.getBirdListOfMother().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
                .mother(bird.getMother())
                .cage(bird.getCage())
                .birdCageHistories(bird.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistoryDto).collect(Collectors.toList()))
                .birdReproductions(bird.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList()))
                .build();
    }
}
