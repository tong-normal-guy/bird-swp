package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdResponse.*;
import com.example.birdReproductionManagement.dto.UpdateBirdReproductionDTO;
import com.example.birdReproductionManagement.entity.Bird;

import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.Sex;


public class BirdMapper {
    public static Bird mapToBird(BirdDTO birdDto){
        return Bird.builder()
//                .id(Long.valueOf(birdDto.getId()))
                .sex(Sex.valueOf(birdDto.getSex()))
                .hatchDate(birdDto.getHatchDate())
                .swingBranchDate(birdDto.getSwingBranchDate())
                .adultBirdDate(birdDto.getAdultBirdDate())
                .ageRange(birdDto.getAgeRange())
                .mutation(birdDto.getMutation())
                .mutationRate(birdDto.getMutationRate())
                .isAlive(birdDto.getIsAlive())
                .status(birdDto.getStatus())
                .image(birdDto.getImage())
                .featherColor(birdDto.getFeatherColor())
                .weight(birdDto.getWeight())
//                .birdType(BirdTypeMapper.mapToBirdType(birdDto.getBirdType()))
//                .birdListOfFather(birdDto.getBirdListOfFather().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))

//                .birdListOfMother(birdDto.getBirdListOfMother().stream().map(BirdMapper::mapToBird).collect(Collectors.toList()))

//                .cage(CageMapper.mapToCage(birdDto.getCage()))
//                .birdCageHistories(birdDto.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistory).collect(Collectors.toList()))
//                .birdReproductions(birdDto.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproduction).collect(Collectors.toList()))
                .build();
    }

    public static BirdDTO mapToBirdDto(Bird bird){
        if (bird.getCage() != null){
            return BirdDTO.builder()
                    .birdId(String.valueOf(bird.getId()))
                    .sex(bird.getSex().name())
                    .hatchDate(bird.getHatchDate())
                    .swingBranchDate(bird.getSwingBranchDate())
                    .adultBirdDate(bird.getAdultBirdDate())
                    .ageRange(bird.getAgeRange())
                    .mutation(bird.getMutation())
                    .mutationRate(bird.getMutationRate())
                    .superReproduct(bird.getSuperReproduct())
                    .isAlive(bird.getIsAlive())
                    .status(bird.getStatus())
                    .image(bird.getImage())
                    .featherColor(bird.getFeatherColor())
                    .weight(bird.getWeight())
                    .birdTypeName(bird.getBirdType().getName())
                    .cageId(String.valueOf(bird.getCage().getId()))
                    .cage(CageMapper.mapToCageDto(bird.getCage()))
                    .birdType(BirdTypeMapper.mapToBirdTypeDto(bird.getBirdType()))
//                .birdListOfFather(bird.getBirdListOfFather().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .birdListOfMother(bird.getBirdListOfMother().stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList()))
//                .cage(CageMapper.mapToCageDto(bird.getCage()))
//                .birdCageHistories(bird.getBirdCageHistories().stream().map(BirdCageHistoryMapper::mapToBirdCageHistoryDto).collect(Collectors.toList()))
//                .birdReproductions(bird.getBirdReproductions().stream().map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList()))
                    .build();
        }
        return BirdDTO.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(bird.getSex().name())
                .hatchDate(bird.getHatchDate())
                .swingBranchDate(bird.getSwingBranchDate())
                .adultBirdDate(bird.getAdultBirdDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .superReproduct(bird.getSuperReproduct())
                .isAlive(bird.getIsAlive())
                .status(bird.getStatus())
                .image(bird.getImage())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .birdTypeName(bird.getBirdType().getName())
                .birdType(BirdTypeMapper.mapToBirdTypeDto(bird.getBirdType()))
                .build();
    }

    public static Bird4CageDetailDTOResponse map2Birdd4CageDetailDTO (Bird bird){
        return Bird4CageDetailDTOResponse.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(String.valueOf(bird.getSex()))
//                .hatchDate(bird.getHatchDate())
                .actEggHatchDate(bird.getHatchDate())
                .actSwingBranchDate(bird.getSwingBranchDate())
                .actAdultBirdDate(bird.getAdultBirdDate())
                .ageRange(bird.getAgeRange())
                .isAlive(bird.getIsAlive())
                .birdType(BirdTypeMapper.map2BirdtypeDTO(bird.getBirdType()))
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .image(bird.getImage())
                .build();
    }
    public static Bird4ProcessDTOResponse map2Bird4ProcessDTO(Bird bird){
        return Bird4ProcessDTOResponse.builder()
                .birdId(bird.getId() + "")
                .sex(bird.getSex() + "")
                .hatchDate(bird.getHatchDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .superReproduct(bird.getSuperReproduct())
                .superReproduct(bird.getSuperReproduct())
                .isAlive(bird.getIsAlive())
                .image(bird.getImage())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .build();
    }

    public static Bird mapToBird(UpdateBirdReproductionDTO updateBirdReproductionDTO){
        return Bird.builder()
                .sex(Sex.valueOf(updateBirdReproductionDTO.getSex()))
                .image(updateBirdReproductionDTO.getImage())
                .weight(updateBirdReproductionDTO.getWeight())
                .hatchDate(updateBirdReproductionDTO.getHatchDate())
                .build();
    }

    public static BirdDetailReponseDTO mapToBirdDetailReponseDTO(Bird bird){
        if (bird.getCage() != null){
            return BirdDetailReponseDTO.builder()
                    .birdId(String.valueOf(bird.getId()))
                    .sex(bird.getSex().name())
                    .hatchDate(bird.getHatchDate())
                    .swingBranchDate(bird.getSwingBranchDate())
                    .adultBirdDate(bird.getAdultBirdDate())
                    .ageRange(bird.getAgeRange())
                    .mutation(bird.getMutation())
                    .mutationRate(bird.getMutationRate())
                    .superReproduct(bird.getSuperReproduct())
                    .isAlive(bird.getIsAlive())
                    .status(bird.getStatus())
                    .image(bird.getImage())
                    .featherColor(bird.getFeatherColor())
                    .weight(bird.getWeight())
                    .birdTypeName(bird.getBirdType().getName())
                    .cageId(String.valueOf(bird.getCage().getId()))
                    .cage(bird.getCage().getLocation())
//                    .cage(CageMapper.mapToCageDto(bird.getCage()))
//                    .birdType(BirdTypeMapper.mapToBirdTypeDto(bird.getBirdType()))
                    .build();
        }
        return BirdDetailReponseDTO.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(bird.getSex().name())
                .hatchDate(bird.getHatchDate())
                .swingBranchDate(bird.getSwingBranchDate())
                .adultBirdDate(bird.getAdultBirdDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .superReproduct(bird.getSuperReproduct())
                .isAlive(bird.getIsAlive())
                .status(bird.getStatus())
                .image(bird.getImage())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .birdTypeName(bird.getBirdType().getName())
//                .birdType(BirdTypeMapper.mapToBirdTypeDto(bird.getBirdType()))
                .build();
    }

    public static DescendantResponseDTO mapToDescendantResponseDTO(Bird bird){
        if (bird.getCage() != null){
            return DescendantResponseDTO.builder()
                    .birdId(String.valueOf(bird.getId()))
                    .sex(bird.getSex().name())
                    .hatchDate(bird.getHatchDate())
                    .ageRange(bird.getAgeRange())
                    .mutation(bird.getMutation())
                    .mutationRate(bird.getMutationRate())
                    .isAlive(bird.getIsAlive())
                    .image(bird.getImage())
                    .featherColor(bird.getFeatherColor())
                    .weight(bird.getWeight())
                    .birdTypeName(bird.getBirdType().getName())
                    .cageId(String.valueOf(bird.getCage().getId()))
                    .build();
        }
        return DescendantResponseDTO.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(bird.getSex().name())
                .hatchDate(bird.getHatchDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .isAlive(bird.getIsAlive())
                .image(bird.getImage())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .birdTypeName(bird.getBirdType().getName())
                .build();
    }

    public static BirdForListResponseDTO mapToBirdForListResponseDTO(Bird bird){
        if(bird.getCage() != null){
            return BirdForListResponseDTO.builder()
                    .birdId(String.valueOf(bird.getId()))
//                    .cageId(String.valueOf(bird.getCage().getId()))
                    .cage(CageMapper.mapToCageDto(bird.getCage()))
                    .sex(bird.getSex().name())
                    .hatchDate(bird.getHatchDate())
                    .ageRange(bird.getAgeRange())
                    .mutation(bird.getMutation())
                    .mutationRate(bird.getMutationRate())
                    .featherColor(bird.getFeatherColor())
                    .weight(bird.getWeight())
                    .image(bird.getImage())
                    .isAlive(bird.getIsAlive())
                    .status(bird.getStatus())
                    .birdTypeName(bird.getBirdType().getName())
                    .build();
        }
        return BirdForListResponseDTO.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(bird.getSex().name())
                .hatchDate(bird.getHatchDate())
                .ageRange(bird.getAgeRange())
                .mutation(bird.getMutation())
                .mutationRate(bird.getMutationRate())
                .featherColor(bird.getFeatherColor())
                .weight(bird.getWeight())
                .image(bird.getImage())
                .isAlive(bird.getIsAlive())
                .status(bird.getStatus())
                .birdTypeName(bird.getBirdType().getName())
                .build();

    }

    public static BirdForPedigreeResponseDTO mapToBirdForPedigreeResponseDTO(Bird bird){
        return BirdForPedigreeResponseDTO.builder()
                .birdId(String.valueOf(bird.getId()))
                .sex(bird.getSex().name())
                .hatchDate(bird.getHatchDate())
                .build();
    }

    public static BirdForReproductionListReponseDTO mapToBirdForReproductionListReponseDTO(Bird bird){
        return BirdForReproductionListReponseDTO.builder()
                .birdId(String.valueOf(bird.getId()))
                .actEggHatchDate(bird.getHatchDate())
                .actSwingBranchDate(bird.getSwingBranchDate())
                .actAdultBirdDate(bird.getAdultBirdDate())
                .build();
    }
}
