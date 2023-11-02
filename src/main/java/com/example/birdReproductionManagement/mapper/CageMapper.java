package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.CageResponse.Cage4ListDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.Cage;

public class CageMapper {
    public static Cage mapToCage(CageDTO cageDto){
        return Cage.builder()
//                .id(cageDto.getId())
                .location(cageDto.getLocation())
                .quantity(cageDto.getQuantity())
                .available(cageDto.getAvailable())
//                .cageType(CageTypeMapper.mapToCageType(cageDto.getCageType()))
//                .birdList(cageDto.getBirdList().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))
//                .birdCageHistories(cageDto.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistory).collect(Collectors.toList()))
//                .reproductionProcesses(cageDto.getReproductionProcesses().stream().map(ReproductionProcessMapper::mapToReproductionProcess).collect(Collectors.toList()))
//                .workDivisions(cageDto.getWorkDivisions().stream().map(WorkDivisionMapper::mapToWorkDivision).collect(Collectors.toList()))
                .build();
    }

    public static CageDTO mapToCageDto(Cage cage){
        if(cage.getUser() == null){
            return CageDTO.builder()
                    .cageId(String.valueOf(cage.getId()))
                    .location(cage.getLocation())
                    .quantity(cage.getQuantity())
                    .available(cage.getAvailable())
//                .cageType(CageTypeMapper.mapToCageTypeDto(cage.getCageType()))
//                .birdList(cage.getBirdList().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .birdCageHistories(cage.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistoryDto).collect(Collectors.toList()))
//                .reproductionProcesses(cage.getReproductionProcesses().stream().map(ReproductionProcessMapper::mapToReproductionProcessDto).collect(Collectors.toList()))
//                .workDivisions(cage.getWorkDivisions().stream().map(WorkDivisionMapper::mapToWorkDivisionDto).collect(Collectors.toList()))
                    .build();
        }
        return CageDTO.builder()
                .cageId(String.valueOf(cage.getId()))
//                .code(cage.getLocation() + String.valueOf(cage.getId()))
                .location(cage.getLocation())
                .quantity(cage.getQuantity())
                .available(cage.getAvailable())
                .user(UserMapper.mapToUserDto(cage.getUser()))
//                .cageType(CageTypeMapper.mapToCageTypeDto(cage.getCageType()))
//                .birdList(cage.getBirdList().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .birdCageHistories(cage.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistoryDto).collect(Collectors.toList()))
//                .reproductionProcesses(cage.getReproductionProcesses().stream().map(ReproductionProcessMapper::mapToReproductionProcessDto).collect(Collectors.toList()))
//                .workDivisions(cage.getWorkDivisions().stream().map(WorkDivisionMapper::mapToWorkDivisionDto).collect(Collectors.toList()))
                .build();
    }
    public static Cage4ListDTO map2Cage4ListDTO(Cage cage){
        return Cage4ListDTO.builder()
                .cageId(String.valueOf(cage.getId()))
                .location(cage.getLocation())
                .quantity(cage.getQuantity())
                .available(cage.getAvailable())
                .build();
    }

    public static CageDetailDTOResponse mapToCageDetailDTOResponse(Cage cage){
        return CageDetailDTOResponse.builder()
                .cageId(String.valueOf(cage.getId()))
                .location(cage.getLocation())
                .quantity(cage.getQuantity())
                .available(cage.getAvailable())
                .build();
    }

}
