package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.SystemCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemCheckServiceImpl implements SystemCheckService {
    private final ReproductionProcessRepository processRepository;
    private final BirdReproductionRepository reproductionRepository;
    @Override
    public void performSystemCheck() {
        List<ReproductionProcess> processes = processRepository.findAllByIsDoneFalse();
        List<BirdReproduction> reproductions;
        for ( ReproductionProcess process: processes ) {
            reproductions = process.getBirdReproductions();
            for ( BirdReproduction reproduction: reproductions ) {
                if (reproduction.getReproductionRole() == ReproductionRole.EGG){

                }
            }
        }
    }
}
