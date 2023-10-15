package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdTypeDto;

import java.util.List;

public interface BirdTypeService {
    List<BirdTypeDto> findAllBirdTypes();
    BirdTypeDto createBirdType(BirdTypeDto birdTypeDto);
    void deleteBirdType(Long id);
    BirdTypeDto updateBirdType(Long id, BirdTypeDto birdTypeDto);
    List<BirdTypeDto> searchBirdTypeByName(String query);
}
