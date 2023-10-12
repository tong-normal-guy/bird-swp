package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.ReproductionProcessDto;

import java.util.List;

public interface ReproductionProcessService {
    List<ReproductionProcessDto> findAllReproductionProcess();
    ReproductionProcessDto addReproductionProcess(ReproductionProcessDto reproductionProcessDto);
    void deleteReproductionProcess(Long id);
    ReproductionProcessDto updateReproductionProcess(Long id, ReproductionProcessDto reproductionProcessDto);
}
