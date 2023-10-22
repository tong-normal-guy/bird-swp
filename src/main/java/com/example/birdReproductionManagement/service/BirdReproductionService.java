package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.EggDto;

import java.util.List;

public interface BirdReproductionService {
    List<BirdReproductionDto> findAllBirdReproductions();
    List<BirdReproductionDto> createBirdReproduction(Long processId, EggDto eggDto);
    BirdReproductionDto updateBirdReproduction(Long id, BirdReproductionDto birdReproductionDto);
}
