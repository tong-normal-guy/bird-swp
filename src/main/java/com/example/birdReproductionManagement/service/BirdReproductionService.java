package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.EggDTO;
import com.example.birdReproductionManagement.dto.UpdateBirdReproductionDTO;

import java.util.List;

public interface BirdReproductionService {
    List<BirdReproductionDTO> findAllBirdReproductions();
    List<BirdReproductionDTO> createBirdReproduction(Long cageId, EggDTO eggDto);
    BirdReproductionDTO updateBirdReproduction(Long id, UpdateBirdReproductionDTO updateBirdReproductionDTO);
    List<BirdReproductionDTO> findChildOfProcess(Long id);
    void deleteBirdReproduction(Long id);

}
