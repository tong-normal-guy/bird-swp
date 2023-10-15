package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.EggDto;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.exceptions.BirdReproductionNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.BirdReproductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirdReproductionServiceImpl implements BirdReproductionService {
    private BirdReproductionRepository birdReproductionRepository;
    private ReproductionProcessRepository reproductionProcessRepository;
    @Autowired
    public BirdReproductionServiceImpl(BirdReproductionRepository birdReproductionRepository, ReproductionProcessRepository reproductionProcessRepository) {
        this.birdReproductionRepository = birdReproductionRepository;
        this.reproductionProcessRepository = reproductionProcessRepository;
    }


    @Override
    public List<BirdReproductionDto> addEggs(EggDto eggDto) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(eggDto.getProcessId()).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Egg could not be add."));
        List<BirdReproduction> eggsList = new ArrayList<>();
        for (int i = 0; i < eggDto.getNumber(); i++){
            BirdReproduction birdReproduction = new BirdReproduction();
            birdReproduction.setActEggHatchDate(eggDto.getHatchDate());
            birdReproduction.setReproductionProcess(reproductionProcess);
            eggsList.add(birdReproductionRepository.save(birdReproduction));
        }
        return eggsList.stream().map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList());
    }

    @Override
    public BirdReproductionDto updateBirdReproduction(BirdReproductionDto birdReproductionDto) {
        BirdReproduction birdReproduction = birdReproductionRepository.findById(birdReproductionDto.getId()).orElseThrow(()
                -> new BirdReproductionNotFoundException("Egg could not be updated."));
        birdReproductionDto.setId(birdReproduction.getId());
        if(!birdReproductionDto.getEggStatus().equals("In development")
                && !birdReproductionDto.getEggStatus().equals("Hatched")){
            birdReproductionDto.setIsFail(true);
        }
        return BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository.save(BirdReproductionMapper
                .mapToBirdReproduction(birdReproductionDto)));
    }
}
