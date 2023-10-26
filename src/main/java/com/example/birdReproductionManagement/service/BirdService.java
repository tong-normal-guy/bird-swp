package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDetailReponseDTO;

import java.util.List;

public interface BirdService {
    List<BirdDetailReponseDTO> findAllBirds();
    BirdDTO updateBird(Long id, BirdDTO birdDto);
    void deleteBird(Long id);
    BirdDTO createBird(BirdDTO birdDto);
    List<BirdDTO> findByCage(Long id);
    BirdDTO updateBirdByFields(Long id, BirdDTO birdDto);
    List<BirdDTO> findBySex(String sex);
}
