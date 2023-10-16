package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdDto;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.mapper.BirdTypeMapper;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.model.Bird;
import com.example.birdReproductionManagement.model.BirdType;
import com.example.birdReproductionManagement.model.Cage;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdTypeRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirdServiceImpl implements BirdService {
    private BirdRepository birdRepository;
    private BirdTypeRepository birdTypeRepository;
    private CageRepository cageRepository;

    public BirdServiceImpl(BirdRepository birdRepository, BirdTypeRepository birdTypeRepository, CageRepository cageRepository) {
        this.birdRepository = birdRepository;
        this.birdTypeRepository = birdTypeRepository;
        this.cageRepository = cageRepository;
    }

//    @Autowired

    @Override
    public List<BirdDto> findAllBirds() {
        List<Bird> birds = birdRepository.findAll();
//         List<BirdDto> birdDtos = new ArrayList<>();
//         for (BirdDto birdDto : birdDtos) {
//             Bird bird = BirdMapper.mapToBird(birdDto);
//             birds.add(bird);
//         }
        return birds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }



    @Override
    public BirdDto updateBird(BirdDto birdDto, Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be updated."));
        birdDto.setId(bird.getId());
        Bird updatedBird = birdRepository.save(BirdMapper.mapToBird(birdDto));
        return BirdMapper.mapToBirdDto(updatedBird);
    }

    @Override
    public BirdDto deleteBird(Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be deleted."));

        birdRepository.delete(bird);

        return BirdMapper.mapToBirdDto(bird);
    }

    @Override
    public BirdDto createBird(BirdDto birdDto) {
        BirdType birdType = birdTypeRepository.findByName(birdDto.getBirdTypeName());
        Cage cage = cageRepository.findById(birdDto.getCageId()).orElseThrow(()
            -> new CageNotFoundException("Bird could not be created."));
        birdDto.setBirdType(BirdTypeMapper.mapToBirdTypeDto(birdType));
        birdDto.setCage(CageMapper.mapToCageDto(cage));
        return BirdMapper.mapToBirdDto(birdRepository.save(BirdMapper.mapToBird(birdDto)));
    }
}
