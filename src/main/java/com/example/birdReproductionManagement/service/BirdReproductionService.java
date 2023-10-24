package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.EggDTO;

import java.util.List;

public interface BirdReproductionService {
    List<BirdReproductionDTO> findAllBirdReproductions();
    List<BirdReproductionDTO> createBirdReproduction(Long processId, EggDTO eggDto);
    BirdReproductionDTO updateBirdReproduction(Long id, BirdReproductionDTO birdReproductionDto);
    List<BirdReproductionDTO> findChildOfProcess(Long id);

}
