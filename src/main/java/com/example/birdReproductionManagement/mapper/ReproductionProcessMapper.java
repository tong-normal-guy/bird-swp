package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.ReproductionProcessDto;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.ReproductionProcess;

public class ReproductionProcessMapper {
    public static ReproductionProcess mapToReproductionProcess(ReproductionProcessDto reproductionProcessDto){
        return ReproductionProcess.builder()
                .id(reproductionProcessDto.getId())
                .pairingDate(reproductionProcessDto.getPairingDate())
//                .eggLaidDate(reproductionProcessDto.getEggLaidDate())
                .expEggHatchDate(reproductionProcessDto.getExpEggHatchDate())
//                .actEggHatchDate(reproductionProcessDto.getActEggHatchDate())
                .expSwingBranch(reproductionProcessDto.getExpSwingBranch())
//                .actSwingBranch(reproductionProcessDto.getActSwingBranch())
                .expAdultBirdDate(reproductionProcessDto.getExpAdultBirdDate())
//                .actAdultBirdDate(reproductionProcessDto.getActAdultBirdDate())
                .totalEgg(reproductionProcessDto.getTotalEgg())
                .stage(reproductionProcessDto.getStage())
                .failEgg(reproductionProcessDto.getFailEgg())
                .isDone(reproductionProcessDto.getIsDone())
//                .cage(CageMapper.mapToCage(reproductionProcessDto.getCage()))
//                .birdReproductions(reproductionProcessDto.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproduction).collect(Collectors.toList()))
                .build();
    }

    public static ReproductionProcessDto mapToReproductionProcessDto(ReproductionProcess reproductionProcess){
        return ReproductionProcessDto.builder()
                .id(reproductionProcess.getId())
                .pairingDate(reproductionProcess.getPairingDate())
//                .eggLaidDate(reproductionProcess.getEggLaidDate())
                .expEggHatchDate(reproductionProcess.getExpEggHatchDate())
//                .actEggHatchDate(reproductionProcess.getActEggHatchDate())
                .expSwingBranch(reproductionProcess.getExpSwingBranch())
//                .actSwingBranch(reproductionProcess.getActSwingBranch())
                .expAdultBirdDate(reproductionProcess.getExpAdultBirdDate())
//                .actAdultBirdDate(reproductionProcess.getActAdultBirdDate())
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
                .id(reproductionProcess.getId())
                .pairingDate(reproductionProcess.getPairingDate())
                .stage(reproductionProcess.getStage())
                .failEgg(reproductionProcess.getFailEgg())
                .totalEgg(reproductionProcess.getTotalEgg())
                .expEggHatchDate(reproductionProcess.getExpEggHatchDate())
                .expSwingBranch(reproductionProcess.getExpSwingBranch())
                .expAdultBirdDate(reproductionProcess.getExpAdultBirdDate())
                .build();
    }
}
