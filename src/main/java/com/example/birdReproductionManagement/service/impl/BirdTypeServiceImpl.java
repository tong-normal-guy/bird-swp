package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdParentDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.MutationBirdListDTO;
import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessInitDTOResponse;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.SuperReproductDTO;
import com.example.birdReproductionManagement.dto.NormalBirdListDTO;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.Sex;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.BirdTypeNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.mapper.BirdTypeMapper;
import com.example.birdReproductionManagement.utils.MyUtils;
import com.example.birdReproductionManagement.entity.BirdType;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdTypeRepository;
import com.example.birdReproductionManagement.service.BirdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirdTypeServiceImpl implements BirdTypeService {
    @Autowired
    private BirdTypeRepository birdTypeRepository;
    @Autowired
    private BirdRepository birdRepository;
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
        BirdType newBirdType = BirdTypeMapper.mapToBirdType(birdTypeDto);
        newBirdType.setId(id);
        return BirdTypeMapper.mapToBirdTypeDto(birdTypeRepository.save(newBirdType));
    }

    @Override
    public List<BirdType4ProcessDTOResponse> getType4Process() {
        List<BirdType4ProcessDTOResponse> types = birdTypeRepository.findAll().stream().map(BirdTypeMapper::map2BirdType4ProcessDTO).collect(Collectors.toList());
        for (BirdType4ProcessDTOResponse birdType4ProcessDTOResponse: types) {
            // xu ly mutation list cua loai cua chim do
            MutationBirdListDTO mutationBirdListDTO = new MutationBirdListDTO();
            // xu ly chim mai mutation cua loai do
            List<Bird4ProcessDTOResponse> mutaionHens =  birdRepository
                    .findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdTypeSortedByMutationRateDesc(Sex.FEMALE,Long.parseLong(birdType4ProcessDTOResponse.getTypeId()) )
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            // xu ly chim duc mutation cua loai do
            List<Bird4ProcessDTOResponse> mutationCocks =  birdRepository
                    .findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdTypeSortedByMutationRateDesc(Sex.MALE,Long.parseLong(birdType4ProcessDTOResponse.getTypeId()) )
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            // set vao list mutation dto
            mutationBirdListDTO.setCock(mutationCocks);
            mutationBirdListDTO.setHen(mutaionHens);
            // set vao list mutation cua loai do
            birdType4ProcessDTOResponse.setMutationBirdList(mutationBirdListDTO);

// ............................................

            // xu ly normal list cua loai chim do
            NormalBirdListDTO normalBirdListDTO = new NormalBirdListDTO();
            // xu ly list chim mai
            List<Bird4ProcessDTOResponse> normalHens = birdRepository
                    .findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(Sex.FEMALE,Long.parseLong(birdType4ProcessDTOResponse.getTypeId()) )
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            // xu ly chim trong
            List<Bird4ProcessDTOResponse> normalCocks = birdRepository
                    .findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(Sex.MALE, Long.parseLong(birdType4ProcessDTOResponse.getTypeId()) )
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            // them list chim duc cai vo normal list
            normalBirdListDTO.setHen(normalHens);
            normalBirdListDTO.setCock(normalCocks);
            // them normal list vo type
            birdType4ProcessDTOResponse.setNormalBirdList(normalBirdListDTO);

// ............................................

            // xu ly superReproduct list cua loai chim do
            SuperReproductDTO superReproductDTO = new SuperReproductDTO();
            // xu ly list chim mai
            List<Bird4ProcessDTOResponse> superReproductHens = MyUtils
                    .findSortedBirdBySuperReproduct(Sex.FEMALE,Long.parseLong(birdType4ProcessDTOResponse.getTypeId()),birdRepository )
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            // xu ly chim trong
            List<Bird4ProcessDTOResponse> superReproductCocks = MyUtils
                    .findSortedBirdBySuperReproduct(Sex.MALE,Long.parseLong(birdType4ProcessDTOResponse.getTypeId()),birdRepository )
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            // them list chim duc cai vo superReproduct list
            superReproductDTO.setHen(superReproductHens);
            superReproductDTO.setCock(superReproductCocks);
            // them superReproduct list vo type
            birdType4ProcessDTOResponse.setSuperReproductDTO(superReproductDTO);
        }

        return types;
    }
    @Override
    public List<BirdType4ProcessInitDTOResponse> getType4ProcessInit() {
        List<BirdType4ProcessInitDTOResponse> birdTypeDTOs = birdTypeRepository.findAll().stream().map(BirdTypeMapper::map2BirdType4ProcessInitDTO).collect(Collectors.toList());
        for (BirdType4ProcessInitDTOResponse birdTypeDTO:birdTypeDTOs) {
//      HENS
            List<Bird4ProcessDTOResponse> hens = birdRepository.findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(Sex.FEMALE, Long.parseLong(birdTypeDTO.getTypeId()))
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            birdTypeDTO.setHen(hens);
//      COCKS
            List<Bird4ProcessDTOResponse> cocks = birdRepository.findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(Sex.MALE, Long.parseLong(birdTypeDTO.getTypeId()))
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            birdTypeDTO.setCock(cocks);
        }
        return birdTypeDTOs;
    }
}
