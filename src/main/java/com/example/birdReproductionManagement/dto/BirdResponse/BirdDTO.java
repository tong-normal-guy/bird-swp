package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdDTO {
    private String birdId;
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
    private BirdTypeDTO birdType;
//    private List<BirdDto> birdListOfFather;
//    private List<BirdDto> birdListOfMother;
    private CageDTO cage;
    private String cageId;
//    private List<BirdCageHistoryDto> birdCageHistories;
//    private List<BirdReproductionDto> birdReproductions;
}
