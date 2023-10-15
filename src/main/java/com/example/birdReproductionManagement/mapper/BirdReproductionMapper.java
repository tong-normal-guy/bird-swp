package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.model.BirdReproduction;
import com.example.birdReproductionManagement.model.ReproductionRole;

public class BirdReproductionMapper {
    public static BirdReproduction mapToBirdReproduction (BirdReproductionDto birdReproductionDto){
        return BirdReproduction.builder()
                .id(birdReproductionDto.getId())
                .bird(BirdMapper.mapToBird(birdReproductionDto.getBird()))
                .actEggHatchDate(birdReproductionDto.getActEggHatchDate())
                .actSwingBranch(birdReproductionDto.getActSwingBranch())
                .actAdultBirdDate(birdReproductionDto.getActAdultBirdDate())
                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcess(birdReproductionDto.getReproductionProcess()))
                .reproductionRole(ReproductionRole.valueOf(birdReproductionDto.getReproductionRole()))
                .build();
    }

    public static BirdReproductionDto mapToBirdReproductionDto (BirdReproduction birdReproduction){
        return BirdReproductionDto.builder()
                .id(birdReproduction.getId())
                .bird(BirdMapper.mapToBirdDto(birdReproduction.getBird()))
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .actSwingBranch(birdReproduction.getActSwingBranch())
                .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .build();
    }

    public static Bird4CageDetailDTOResponse map2Bird4CageDetailDTO(BirdReproduction birdReproduction){
        return Bird4CageDetailDTOResponse.builder()
                .id(birdReproduction.getId())
                .isMale(birdReproduction.getBird().getSex())
                .birdType(birdReproduction.getBird().getBirdType().getName())
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .eggStatus(birdReproduction.getEggStatus())
                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .mutationRate(birdReproduction.getBird().getMutationRate())
                .weight(birdReproduction.getBird().getWeight())
                .build();
    }
}
