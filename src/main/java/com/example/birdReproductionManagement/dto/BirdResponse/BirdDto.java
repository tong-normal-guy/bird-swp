package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.dto.CageResponse.CageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdDto {
    private Long id;
    private String sex;
    private Date hatchDate;
    private String ageRange;
    private String mutation;
    private Float mutationRate;
    private Boolean isAlive;
    private String image;
    private String featherColor;
    private Float weight;
    private String birdTypeName;
//    private List<BirdDto> birdListOfFather;
//    private List<BirdDto> birdListOfMother;
    private CageDto cage;
    private Long cageId;
//    private List<BirdCageHistoryDto> birdCageHistories;
//    private List<BirdReproductionDto> birdReproductions;
}
