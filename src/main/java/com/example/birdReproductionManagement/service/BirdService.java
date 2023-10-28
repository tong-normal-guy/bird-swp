package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDetailReponseDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdForListResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BirdService {
    List<BirdForListResponseDTO> findAllBirds();
    BirdDetailReponseDTO getBirdDetailById(Long id);
    BirdDTO updateBird(Long id, BirdDTO birdDto);
    void deleteBird(Long id);
    BirdDTO createBird(BirdDTO birdDto);
    List<BirdDTO> findByCage(Long id);
    BirdDTO updateBirdByFields(Long id, BirdDTO birdDto);
    List<BirdDTO> findBySex(String sex);
    List<BirdDTO> findOutcastBirds();
}
