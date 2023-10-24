package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;

import java.util.List;

public interface BirdService {
    List<BirdDTO> findAllBirds();
    BirdDTO updateBird(Long id, BirdDTO birdDto);
    void deleteBird(Long id);
    BirdDTO createBird(BirdDTO birdDto);
    List<BirdDTO> findByCage(Long id);
    BirdDTO updateBirdByFields(Long id, BirdDTO birdDto);
    List<BirdDTO> findBySex(String sex);
}
