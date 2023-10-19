package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.CageResponse.Cage4ListDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDto;

import java.util.List;

public interface CageService {
    List<CageDto> findAllCages();
    List<CageDetailDTOResponse> pickaCages(); // 4 list cages hv id, location, quantity.

    CageDto getDetailById(Long id);
    CageDto addCage(CageDto cageDto);
    CageDto updateCage(Long id, CageDto cageDto);
    void deleteCage(Long id);
    List<CageDto> findByLocation(String location);

}
