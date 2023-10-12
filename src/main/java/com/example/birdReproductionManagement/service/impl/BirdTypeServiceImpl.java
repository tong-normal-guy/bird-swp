package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.BirdTypeNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdTypeMapper;
import com.example.birdReproductionManagement.model.BirdType;
import com.example.birdReproductionManagement.repository.BirdTypeRepository;
import com.example.birdReproductionManagement.service.BirdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirdTypeServiceImpl implements BirdTypeService {
    private BirdTypeRepository birdTypeRepository;
    @Autowired
    public BirdTypeServiceImpl(BirdTypeRepository birdTypeRepository) {
        this.birdTypeRepository = birdTypeRepository;
    }

    @Override
    public List<BirdTypeDto> findAllBirdTypes() {
        return birdTypeRepository.findAll().stream().map(BirdTypeMapper::mapToBirdTypeDto).collect(Collectors.toList());
    }

    @Override
    public BirdTypeDto createBirdType(BirdTypeDto birdTypeDto) {
        return BirdTypeMapper.mapToBirdTypeDto(birdTypeRepository.save(BirdTypeMapper.mapToBirdType(birdTypeDto)));
    }

    @Override
    public void deleteBirdType(Long id) {
        BirdType birdType = birdTypeRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird type could not be deleted because it could not be found."));
        birdTypeRepository.delete(birdType);
    }

    @Override
    public BirdTypeDto updateBirdType(Long id, BirdTypeDto birdTypeDto) {
        BirdType birdType = birdTypeRepository.findById(id).orElseThrow(()
            -> new BirdTypeNotFoundException("Bird type could not be updated."));
        birdTypeDto.setId(birdType.getId());
        return BirdTypeMapper.mapToBirdTypeDto(birdTypeRepository.save(BirdTypeMapper.mapToBirdType(birdTypeDto)));
    }
}
