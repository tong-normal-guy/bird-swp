package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdReForProcessDetailResponseDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdReproductionForBirdDetailResponseDTO;
import com.example.birdReproductionManagement.dto.DashboardResponse.CloseDateReproductionDTOResponse;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;

public class BirdReproductionMapper {
    public static BirdReproduction mapToBirdReproduction (BirdReproductionDTO birdReproductionDto){
        return BirdReproduction.builder()
//                .id(birdReproductionDto.getId())
//                .bird(BirdMapper.mapToBird(birdReproductionDto.getBird()))
                .eggLaidDate(birdReproductionDto.getEggLaidDate())
//                .actEggHatchDate(birdReproductionDto.getActEggHatchDate())
//                .actSwingBranch(birdReproductionDto.getActSwingBranch())
//                .actAdultBirdDate(birdReproductionDto.getActAdultBirdDate())
                .eggType(birdReproductionDto.getEggType())
                .eggStatus(birdReproductionDto.getEggStatus())
                .isFail(birdReproductionDto.isFail())
                .failDate(birdReproductionDto.getFailDate())
                .expEggHatchDate(birdReproductionDto.getExpEggHatchDate())
                .expSwingBranchDate(birdReproductionDto.getExpSwingBranchDate())
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
//                    .actEggHatchDate(birdReproduction.getActEggHatchDate())
//                    .actSwingBranch(birdReproduction.getActSwingBranch())
//                    .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                    .eggType(birdReproduction.getEggType())
                    .eggStatus(birdReproduction.getEggStatus())
                    .isFail(birdReproduction.isFail())
                    .failDate(birdReproduction.getFailDate())
                    .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                    .expSwingBranchDate(birdReproduction.getExpSwingBranchDate())
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
//                .actEggHatchDate(birdReproduction.getActEggHatchDate())
//                .actSwingBranch(birdReproduction.getActSwingBranch())
//                .actAdultBirdDate(birdReproduction.getActAdultBirdDate())
                .eggType(birdReproduction.getEggType())
                .eggStatus(birdReproduction.getEggStatus())
                .isFail(birdReproduction.isFail())
                .failDate(birdReproduction.getFailDate())
                .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                .expSwingBranchDate(birdReproduction.getExpSwingBranchDate())
                .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
//                .reproductionProcess(ReproductionProcessMapper.mapToReproductionProcessDto(birdReproduction.getReproductionProcess()))
                .reproductionProcessId(String.valueOf(birdReproduction.getReproductionProcess().getId()))
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .build();
    }

    public static BirdRe4CageDetailDTOResponse map2Bird4CageDetailDTO(BirdReproduction birdReproduction){
        if (birdReproduction.getBird() != null){
            return BirdRe4CageDetailDTOResponse.builder()
                    .reproductionId(String.valueOf(birdReproduction.getId()))
                    .eggLaidDate(birdReproduction.getEggLaidDate())
                    .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                    .eggType(birdReproduction.getEggType())
                    .eggStatus(String.valueOf(birdReproduction.getEggStatus()))
                    .isFail(birdReproduction.isFail())
                    .failDate(birdReproduction.getFailDate())
//                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                    .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                    .expSwingBranchDate(birdReproduction.getExpSwingBranchDate())
                    .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
                    .bird(BirdMapper.map2Birdd4CageDetailDTO(birdReproduction.getBird()))
                    .build();
        }
        return BirdRe4CageDetailDTOResponse.builder()
                .reproductionId(String.valueOf(birdReproduction.getId()))
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .reproductionRole(String.valueOf(birdReproduction.getReproductionRole()))
                .eggType(birdReproduction.getEggType())
                .eggStatus(String.valueOf(birdReproduction.getEggStatus()))
                .isFail(birdReproduction.isFail())
                .failDate(birdReproduction.getFailDate())
//                .actEggHatchDate(birdReproduction.getActEggHatchDate())
                .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                .expSwingBranchDate(birdReproduction.getExpSwingBranchDate())
                .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
                .build();
    }

    public static BirdReproductionForBirdDetailResponseDTO mapToBirdReproductionForBirdDetailResponseDTO(BirdReproduction birdReproduction){
        return BirdReproductionForBirdDetailResponseDTO.builder()
                .reproductionId(String.valueOf(birdReproduction.getId()))
                .expEggHatchDate(birdReproduction.getExpEggHatchDate())
                .expSwingBranchDate(birdReproduction.getExpSwingBranchDate())
                .expAdultBirdDate(birdReproduction.getExpAdultBirdDate())
                .build();
    }
    public static CloseDateReproductionDTOResponse map2CloseDateReproductionDTO(BirdReproduction birdReproduction){
        return CloseDateReproductionDTOResponse.builder()
                .cageId(birdReproduction.getReproductionProcess().getCage().getId()+"")
                .pairingDate(birdReproduction.getReproductionProcess().getPairingDate())
                .build();
    }

    public static BirdReForProcessDetailResponseDTO mapToBirdReForProcessDetailResponseDTO(BirdReproduction birdReproduction){
        if(birdReproduction.getBird() != null){
            return BirdReForProcessDetailResponseDTO.builder()
                    .reproductionId(birdReproduction.getId().toString())
                    .bird(BirdMapper.mapToBirdForReproductionListReponseDTO(birdReproduction.getBird()))
                    .eggLaidDate(birdReproduction.getEggLaidDate())
                    .eggStatus(birdReproduction.getEggStatus())
                    .build();
        }
        return BirdReForProcessDetailResponseDTO.builder()
                .reproductionId(birdReproduction.getId().toString())
                .eggLaidDate(birdReproduction.getEggLaidDate())
                .eggStatus(birdReproduction.getEggStatus())
                .build();
    }
}
