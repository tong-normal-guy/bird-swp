package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdDto {
    private Long id;
    private Boolean sex;
    private Date hatchDate;
    private String ageRange;
    private String mutation;
    private Float mutationRate;
    private Boolean isAlive;
    private String image;
    private String featherColor;
    private Long weight;
    private BirdTypeDto birdType;
    private String birdTypeName;
//    private List<BirdDto> birdListOfFather;
    private BirdDto father;
    private Long fatherId;
//    private List<BirdDto> birdListOfMother;
    private BirdDto mother;
    private Long motherId;
    private CageDto cage;
    private Long cageId;
//    private List<BirdCageHistoryDto> birdCageHistories;
//    private List<BirdReproductionDto> birdReproductions;
}
