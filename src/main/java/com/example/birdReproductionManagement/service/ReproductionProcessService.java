package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.ReproductionProcessDto;
import com.example.birdReproductionManagement.dto.SeparationPairDto;

import java.util.Date;
import java.util.List;

public interface ReproductionProcessService {
    List<ReproductionProcessDto> findAllReproductionProcess();
    ReproductionProcessDto addReproductionProcess(ReproductionProcessDto reproductionProcessDto);
    void deleteReproductionProcess(Long id);
    ReproductionProcessDto updateReproductionProcess(Long processId, ReproductionProcessDto reproductionProcessDto);
    void updateSeparationDate(SeparationPairDto separationPairDto);
}
