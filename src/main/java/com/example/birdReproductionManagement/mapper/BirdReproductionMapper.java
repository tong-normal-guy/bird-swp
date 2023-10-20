package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionRole;

public class BirdReproductionMapper {
    public static BirdReproduction mapToBirdReproduction (BirdReproductionDto birdReproductionDto){
        return BirdReproduction.builder()
//                .id(birdReproductionDto.getId())
//                .bird(BirdMapper.mapToBird(birdReproductionDto.getBird()))
                .eggLaidDate(birdReproductionDto.getEggLaidDate())
                .actEggHatchDate(birdReproductionDto.getActEggHatchDate())
                .actSwingBranch(birdReproductionDto.getActSwingBranch())
                .actAdultBirdDate(birdReproductionDto.getActAdultBirdDate())
                .eggType(birdReproductionDto.getEggType())
                .eggStatus(birdReproductionDto.getEggStatus())
                .isFail(birdReproductionDto.isFail())
                .failDate(birdReproductionDto.getFailDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcess(birdReproductionDto.getReproductionProcess()))
                .reproductionRole(ReproductionRole.valueOf(birdReproductionDto.getReproductionRole()))
                .build();
    }

    public static BirdReproductionDto mapToBirdReproductionDto (BirdReproduction birdReproduction){
        if(birdReproduction.getReproductionRole().name().equals("EGG")){
            return BirdReproductionDto.builder()
                    .id(String.valueOf(birdReproduction.getId()))
//                    .bird(BirdMapper.mapToBirdDto(birdReproduction.getBird()))
                    .eggLaidDate(birdReproduction.getEggLaidDate())
                    .actEggHatchDate(birdReproduction.getActEggHatchDate())
                    .actSwingBranch(birdReproduction.getActSwingBranch())
                    .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                    .eggType(birdReproduction.getEggType())
                    .eggStatus(birdReproduction.getEggStatus())
                    .isFail(birdReproduction.isFail())
                    .failDate(birdReproduction.getFailDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                    .reproductionProcessId(birdReproduction.getReproductionProcess().getId())
                    .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                    .build();
        }
        return BirdReproductionDto.builder()
                .id(String.valueOf(birdReproduction.getId()))
                .bird(BirdMapper.mapToBirdDto(birdReproduction.getBird()))
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .actSwingBranch(birdReproduction.getActSwingBranch())
                .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                .eggType(birdReproduction.getEggType())
                .eggStatus(birdReproduction.getEggStatus())
                .isFail(birdReproduction.isFail())
                .failDate(birdReproduction.getFailDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                .reproductionProcessId(birdReproduction.getReproductionProcess().getId())
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .build();
    }

    public static BirdRe4CageDetailDTOResponse map2Bird4CageDetailDTO(BirdReproduction birdReproduction){
        return BirdRe4CageDetailDTOResponse.builder()
                .birdReproductionId(String.valueOf(birdReproduction.getId()))
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .eggStatus(String.valueOf(birdReproduction.getEggStatus()))
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .build();
    }
}
