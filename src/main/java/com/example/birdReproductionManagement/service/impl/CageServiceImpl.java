package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.CageDto;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageTypeNotFoundException;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.mapper.CageTypeMapper;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.entity.CageType;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.repository.CageTypeRepository;
import com.example.birdReproductionManagement.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CageServiceImpl implements CageService {
    private CageRepository cageRepository;
    private CageTypeRepository cageTypeRepository;
    @Autowired
    public CageServiceImpl(CageRepository cageRepository, CageTypeRepository cageTypeRepository) {
        this.cageRepository = cageRepository;
        this.cageTypeRepository = cageTypeRepository;
    }

    @Override
    public List<CageDto> findAllCages() {
        return cageRepository.findAll().stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
    }

    @Override
    public CageDto addCage(CageDto cageDto) {
        CageType cageType = cageTypeRepository.findById(cageDto.getCageTypeId()).orElseThrow(()
                -> new CageTypeNotFoundException("Cage could not be added."));
        cageDto.setCageType(CageTypeMapper.mapToCageTypeDto(cageType));
        return CageMapper.mapToCageDto(cageRepository.save(CageMapper.mapToCage(cageDto)));
    }

    @Override
    public CageDto updateCage(Long id, CageDto cageDto) {
        Cage cage = cageRepository.findById(id).orElseThrow(()
                -> new CageNotFoundException("Cage could not be updated."));
        CageType cageType = cageTypeRepository.findById(cageDto.getCageTypeId()).orElseThrow(()
                -> new CageTypeNotFoundException("Cage could not be updated."));
        cageDto.setId(id);
        cageDto.setCageType(CageTypeMapper.mapToCageTypeDto(cageType));
        return CageMapper.mapToCageDto(cageRepository.save(CageMapper.mapToCage(cageDto)));
    }

    @Override
    public void deleteCage(Long id) {
        Cage cage = cageRepository.findById(id).orElseThrow(()
                -> new CageNotFoundException("Cage could not be deleted."));
        cageRepository.delete(cage);
    }
}
