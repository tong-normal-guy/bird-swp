package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;

import java.util.List;

public interface CageService {
    List<CageDTO> viewCageUsable();
    List<CageDTO> findAllCages();
    List<?> pickaCages(Boolean process); // 4 list cages hv id, location, quantity.
    CageDTO getDetailById(Long id);
    CageDTO addCage(CageDTO cageDto);
    CageDTO updateCage(Long id, CageDTO cageDto);
    CageDTO updateCageByFields(Long id, CageDTO cageDto);
    void deleteCage(Long id);
    List<CageDTO> findByLocation(String location, boolean available);
    List<CageDetailDTOResponse> viewCageByLocation(String location);
    BirdDTO addBirdToCage(Long cageId, BirdDTO birdDTO);
}
