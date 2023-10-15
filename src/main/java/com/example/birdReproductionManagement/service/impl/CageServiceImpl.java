package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.Cage4ListDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDto;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageTypeNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.*;
import com.example.birdReproductionManagement.model.BirdReproduction;
import com.example.birdReproductionManagement.model.Cage;
import com.example.birdReproductionManagement.model.CageType;
import com.example.birdReproductionManagement.model.ReproductionProcess;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.repository.CageTypeRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.CageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CageServiceImpl implements CageService {
    private final CageRepository cageRepository;
    private final CageTypeRepository cageTypeRepository;
    private final ReproductionProcessRepository reproductionProcessRepository;
    private final BirdReproductionRepository birdReproductionRepository;
//    private

    @Override
    public List<CageDto> findAllCages() {
        return cageRepository.findAll().stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
    }
    @Override
    public List<Cage4ListDTO> pickaCages() {
        return cageRepository.findAll().stream().map(CageMapper::map2Cage4ListDTO).collect(Collectors.toList());
    }

    @Override
    public CageDetailDTOResponse getDetailById(Long id) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage_Id(id)
                .orElseThrow( () -> new ReproductionProcessNotFoundException("khong co cai long nao theo cage id"));
        List<BirdReproduction> birdReproductions = birdReproductionRepository.findAllByReproductionProcess_Id(reproductionProcess.getId());
        Cage cage = cageRepository.findById(id).orElseThrow( () -> new CageNotFoundException("khong co long nay"));

        Reproduction4CageDetailDTOResponse reproduction4CageDetailDTOResponse = ReproductionProcessMapper.map2Reproduction4CageDetailDTO(reproductionProcess);
        List<Bird4CageDetailDTOResponse> bird4CageDetailDTOResponses = birdReproductions.stream().map(BirdReproductionMapper::map2Bird4CageDetailDTO).collect(Collectors.toList());
        User4CageDetailDTOResponse user4CageDetailDTOResponse = UserEntityMapper.map2User4CageDetailDTO(cage.getUser());

        return CageDetailDTOResponse.builder()
                .cageID(id)
                .reproduction4CageDetailDTOResponse(reproduction4CageDetailDTOResponse)
                .bird4CageDetailDTO(bird4CageDetailDTOResponses)
                .user4CageDetailDTOResponse(user4CageDetailDTOResponse)
                .build();
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
