package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.ReproductionProcessDto;
import com.example.birdReproductionManagement.dto.SeparationPairDto;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.exceptions.BirdReproductionNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.mapper.ReproductionProcessMapper;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReproductionProcessServiceImpl implements ReproductionProcessService {
    private ReproductionProcessRepository reproductionProcessRepository;
    private CageRepository cageRepository;
    private BirdReproductionRepository birdReproductionRepository;
    private BirdRepository birdRepository;
    @Autowired
    public ReproductionProcessServiceImpl(ReproductionProcessRepository reproductionProcessRepository, CageRepository cageRepository, BirdReproductionRepository birdReproductionRepository, BirdRepository birdRepository) {
        this.reproductionProcessRepository = reproductionProcessRepository;
        this.cageRepository = cageRepository;
        this.birdReproductionRepository = birdReproductionRepository;
        this.birdRepository = birdRepository;
    }

    @Override
    public List<ReproductionProcessDto> findAllReproductionProcess() {
        return reproductionProcessRepository.findAll().stream().map(ReproductionProcessMapper::mapToReproductionProcessDto).collect(Collectors.toList());
    }

    @Override
    public ReproductionProcessDto addReproductionProcess(ReproductionProcessDto reproductionProcessDto) {
        Cage cage = cageRepository.findById(reproductionProcessDto.getCageId()).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be created."));
        reproductionProcessDto.setCage(CageMapper.mapToCageDto(cage));
        return ReproductionProcessMapper.mapToReproductionProcessDto(reproductionProcessRepository.save(ReproductionProcessMapper.mapToReproductionProcess(reproductionProcessDto)));
    }

    @Override
    public void deleteReproductionProcess(Long id) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be deleted."));
        reproductionProcessRepository.delete(reproductionProcess);
    }

    @Override
    public ReproductionProcessDto updateReproductionProcess(Long id, ReproductionProcessDto reproductionProcessDto) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be updated."));
        Cage cage = cageRepository.findById(reproductionProcessDto.getCageId()).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be updated."));
        reproductionProcessDto.setCage(CageMapper.mapToCageDto(cage));
        return ReproductionProcessMapper.mapToReproductionProcessDto(reproductionProcessRepository.save(ReproductionProcessMapper.mapToReproductionProcess(reproductionProcessDto)));
    }

    @Override
    public void updateSeparationDate(SeparationPairDto separationPairDto) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(separationPairDto.getProcessId()).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Separation date could not be save to reproduction process."));
        reproductionProcess.setId(separationPairDto.getProcessId());
        reproductionProcess.setSeparationDate(separationPairDto.getSeparationDate());
        reproductionProcessRepository.save(reproductionProcess);
        BirdReproduction birdReproduction = birdReproductionRepository.findFatherInProcess(reproductionProcess.getId());
        Bird fatherBird = birdRepository.findById(birdReproduction.getBird().getId()).orElseThrow(()
                -> new BirdReproductionNotFoundException("Separation date could not be save to reproduction process."));
        Cage newCage = cageRepository.findById(separationPairDto.getCageId()).orElseThrow(()
                -> new CageNotFoundException("Separation date could not be save to reproduction process."));
        fatherBird.setCage(newCage);
        fatherBird.setId(birdReproduction.getBird().getId());
        birdRepository.save(fatherBird);
    }
}
