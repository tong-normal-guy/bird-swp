package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.EggDto;
import com.example.birdReproductionManagement.entity.BirdReproduction;

import java.util.Date;
import java.util.List;

public interface BirdReproductionService {
    List<BirdReproductionDto> addEggs(EggDto eggDto);
    BirdReproductionDto updateBirdReproduction(BirdReproductionDto birdReproductionDto);
}
