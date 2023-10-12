package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.ReproductionProcessDto;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.mapper.ReproductionProcessMapper;
import com.example.birdReproductionManagement.model.Cage;
import com.example.birdReproductionManagement.model.ReproductionProcess;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReproductionProcessServiceImpl implements ReproductionProcessService {
    private ReproductionProcessRepository reproductionProcessRepository;
    private CageRepository cageRepository;
    @Autowired

    public ReproductionProcessServiceImpl(ReproductionProcessRepository reproductionProcessRepository, CageRepository cageRepository) {
        this.reproductionProcessRepository = reproductionProcessRepository;
        this.cageRepository = cageRepository;
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
}
