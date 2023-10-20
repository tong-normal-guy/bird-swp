package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.EggDto;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.BirdReproductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BirdReproductionServiceImpl implements BirdReproductionService {
    private final BirdReproductionRepository birdReproductionRepository;
    private final ReproductionProcessRepository reproductionProcessRepository;
    @Override
    public List<BirdReproductionDto> findAllBirdReproductions() {
        List<BirdReproduction> birdReproductions = birdReproductionRepository.findAll();
        return birdReproductions.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<BirdReproductionDto> createBirdReproduction(Long processId, EggDto eggDto) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(processId).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Egg could not be added."));
        List<BirdReproduction> newEggs = new ArrayList<>();
        for (int i = 0; i < eggDto.getNumber(); i++){
            BirdReproduction newEgg = new BirdReproduction();
            newEgg.setEggLaidDate(eggDto.getLaidDate());
            newEgg.setReproductionProcess(reproductionProcess);
            newEgg.setEggStatus("In development");
            newEgg.setReproductionRole(ReproductionRole.EGG);
            newEggs.add(birdReproductionRepository.save(newEgg));
        }
        return newEggs.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }
}
