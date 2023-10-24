package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionRole;

public class BirdReproductionMapper {
    public static BirdReproduction mapToBirdReproduction (BirdReproductionDTO birdReproductionDto){
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
                .expEggHatchDate(birdReproductionDto.getExpEggHatchDate())
                .expSwingBranch(birdReproductionDto.getExpSwingBranch())
                .expAdultBirdDate(birdReproductionDto.getExpAdultBirdDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcess(birdReproductionDto.getReproductionProcess()))
                .reproductionRole(ReproductionRole.valueOf(birdReproductionDto.getReproductionRole()))
                .build();
    }

    public static BirdReproductionDTO mapToBirdReproductionDto (BirdReproduction birdReproduction){
        if(birdReproduction.getReproductionRole().name().equals("EGG")){
            return BirdReproductionDTO.builder()
                    .reproductionId(String.valueOf(birdReproduction.getId()))
//                    .bird(BirdMapper.mapToBirdDto(birdReproduction.getBird()))
                    .eggLaidDate(birdReproduction.getEggLaidDate())
                    .actEggHatchDate(birdReproduction.getActEggHatchDate())
                    .actSwingBranch(birdReproduction.getActSwingBranch())
                    .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                    .eggType(birdReproduction.getEggType())
                    .eggStatus(birdReproduction.getEggStatus())
                    .isFail(birdReproduction.isFail())
                    .failDate(birdReproduction.getFailDate())
                    .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                    .expSwingBranch(birdReproduction.getExpSwingBranch())
                    .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                    .reproductionProcessId(String.valueOf(birdReproduction.getReproductionProcess().getId()))
                    .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                    .build();
        }
        return BirdReproductionDTO.builder()
                .reproductionId(String.valueOf(birdReproduction.getId()))
                .bird(BirdMapper.mapToBirdDto(birdReproduction.getBird()))
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .actSwingBranch(birdReproduction.getActSwingBranch())
                .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                .eggType(birdReproduction.getEggType())
                .eggStatus(birdReproduction.getEggStatus())
                .isFail(birdReproduction.isFail())
                .failDate(birdReproduction.getFailDate())
                .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                .expSwingBranch(birdReproduction.getExpSwingBranch())
                .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                .reproductionProcessId(String.valueOf(birdReproduction.getReproductionProcess().getId()))
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .build();
    }

    public static BirdRe4CageDetailDTOResponse map2Bird4CageDetailDTO(BirdReproduction birdReproduction){
        return BirdRe4CageDetailDTOResponse.builder()
                .reproductionId(String.valueOf(birdReproduction.getId()))
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .eggType(birdReproduction.getEggType())
                .eggStatus(String.valueOf(birdReproduction.getEggStatus()))
                .isFail(birdReproduction.isFail())
                .failDate(birdReproduction.getFailDate())
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                .expSwingBranch(birdReproduction.getExpSwingBranch())
                .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
                .build();
    }
}
