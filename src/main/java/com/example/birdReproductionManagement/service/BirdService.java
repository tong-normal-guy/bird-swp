package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdDto;
import com.example.birdReproductionManagement.model.Bird;

import java.util.List;

public interface BirdService {
    List<BirdDto> findAllBirds();
    BirdDto updateBird(BirdDto birdDto, Long id);

    BirdDto deleteBird(Long id);

    BirdDto createBird(BirdDto birdDto);
}
