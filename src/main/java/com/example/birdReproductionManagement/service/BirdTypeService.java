package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessDTOResponse;

import java.util.List;

public interface BirdTypeService {
    List<BirdTypeDto> findAllBirdTypes();
    BirdTypeDto createBirdType(BirdTypeDto birdTypeDto);
    void deleteBirdType(Long id);
    BirdTypeDto updateBirdType(Long id, BirdTypeDto birdTypeDto);

    List<BirdType4ProcessDTOResponse> getType4Process();
}
