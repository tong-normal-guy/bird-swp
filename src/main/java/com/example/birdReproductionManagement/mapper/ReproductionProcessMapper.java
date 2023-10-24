package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.ReproductionProcess;

public class ReproductionProcessMapper {
    public static ReproductionProcess mapToReproductionProcess(ReproductionProcessDTO reproductionProcessDto){
        return ReproductionProcess.builder()
//                .id(reproductionProcessDto.getId())
                .pairingDate(reproductionProcessDto.getPairingDate())
                .totalEgg(reproductionProcessDto.getTotalEgg())
                .stage(reproductionProcessDto.getStage())
                .failEgg(reproductionProcessDto.getFailEgg())
                .isDone(reproductionProcessDto.getIsDone())
//                .cage(CageMapper.mapToCage(reproductionProcessDto.getCage()))
//                .birdReproductions(reproductionProcessDto.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproduction).collect(Collectors.toList()))
                .build();
    }

    public static ReproductionProcessDTO mapToReproductionProcessDto(ReproductionProcess reproductionProcess){
        return ReproductionProcessDTO.builder()
                .processId(String.valueOf(reproductionProcess.getId()))
                .pairingDate(reproductionProcess.getPairingDate())
                .totalEgg(reproductionProcess.getTotalEgg())
                .stage(reproductionProcess.getStage())
                .failEgg(reproductionProcess.getFailEgg())
                .isDone(reproductionProcess.getIsDone())
                .cage(CageMapper.mapToCageDto(reproductionProcess.getCage()))
//                .birdReproductions(reproductionProcess.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList()))
                .build();
    }

    public static Reproduction4CageDetailDTOResponse map2Reproduction4CageDetailDTO(ReproductionProcess reproductionProcess){
        return Reproduction4CageDetailDTOResponse.builder()
                .processId(String.valueOf(reproductionProcess.getId()))
                .pairingDate(reproductionProcess.getPairingDate())
                .stage(reproductionProcess.getStage())
                .failEgg(reproductionProcess.getFailEgg())
                .totalEgg(reproductionProcess.getTotalEgg())
                .build();
    }

    public static ProcessForViewAllResponseDTO mapToProcessForViewAllResponseDTO(ReproductionProcess reproductionProcess){
        return ProcessForViewAllResponseDTO.builder()
                .processId(String.valueOf(reproductionProcess.getId()))
                .cageId(String.valueOf(reproductionProcess.getCage().getId()))
                .isDone(reproductionProcess.getIsDone())
                .pairingDate(reproductionProcess.getPairingDate())
                .build();
    }
}
