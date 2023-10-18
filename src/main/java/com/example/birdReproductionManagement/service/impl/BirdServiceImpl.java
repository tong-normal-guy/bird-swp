package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.mapper.BirdTypeMapper;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdType;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdTypeRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirdServiceImpl implements BirdService {
    private BirdRepository birdRepository;
    private BirdTypeRepository birdTypeRepository;
    private CageRepository cageRepository;
    @Autowired
    public BirdServiceImpl(BirdRepository birdRepository, BirdTypeRepository birdTypeRepository, CageRepository cageRepository) {
        this.birdRepository = birdRepository;
        this.birdTypeRepository = birdTypeRepository;
        this.cageRepository = cageRepository;
    }

//    @Autowired

    @Override
    public List<BirdDto> findAllBirds() {
        List<Bird> birds = birdRepository.findAll();
        return birds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }



    @Override
    public BirdDto updateBird(BirdDto birdDto, Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be updated."));
        birdDto.setId(bird.getId());
        Bird updatedBird = BirdMapper.mapToBird(birdDto);
        updatedBird.setBirdType(bird.getBirdType());
        Cage cage;
        if (!bird.getCage().getId().equals(birdDto.getCageId())){
            cage =  cageRepository.findById(birdDto.getCageId()).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
        }else {
            cage = cageRepository.findById(bird.getCage().getId()).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
        }
        updatedBird.setCage(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(updatedBird));
    }

    @Override
    public void deleteBird(Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be deleted."));
        birdRepository.delete(bird);
    }

    @Override
    public BirdDto createBird(BirdDto birdDto) {
        BirdType birdType = birdTypeRepository.findByName(birdDto.getBirdTypeName());
        Cage cage = cageRepository.findById(birdDto.getCageId()).orElseThrow(()
            -> new CageNotFoundException("Bird could not be created."));
        Bird bird = BirdMapper.mapToBird(birdDto);
        bird.setBirdType(birdType);
        bird.setCage(cage);
        cage.setQuantity(cage.getQuantity() + 1);
        cageRepository.save(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(bird));
    }

    @Override
    public List<BirdDto> findByCage(Long id) {
        return birdRepository.findByCage_Id(id).stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }
}
