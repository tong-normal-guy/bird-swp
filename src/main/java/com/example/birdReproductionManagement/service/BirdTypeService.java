package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessDTOResponse;

import java.util.List;

public interface BirdTypeService {
    List<BirdTypeDTO> findAllBirdTypes();
    BirdTypeDTO createBirdType(BirdTypeDTO birdTypeDto);
    void deleteBirdType(Long id);
    BirdTypeDTO updateBirdType(Long id, BirdTypeDTO birdTypeDto);

    List<BirdType4ProcessDTOResponse> getType4Process();
}
