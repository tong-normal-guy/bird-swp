package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;

import java.util.List;
import java.util.Map;

public interface BirdService {
    List<BirdDto> findAllBirds();
    BirdDto updateBird(Long id, BirdDto birdDto);

    void deleteBird(Long id);

    BirdDto createBird(BirdDto birdDto);
    List<BirdDto> findByCage(Long id);
    BirdDto updateBirdByFields(Long id, BirdDto birdDto);
}
