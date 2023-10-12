package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.CageDto;
import com.example.birdReproductionManagement.model.Cage;

import java.util.List;

public interface CageService {
    List<CageDto> findAllCages();
    CageDto addCage(CageDto cageDto);
    CageDto updateCage(Long id, CageDto cageDto);
    void deleteCage(Long id);
}
