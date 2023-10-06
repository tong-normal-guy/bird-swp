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
    private String image;
    private Long weight;
    private BirdType birdType;
    private List<BirdDto> birdListOfFather;
    private Bird father;
    private List<BirdDto> birdListOfMother;
    private Bird mother;
    private Cage cage;
    private List<BirdCageHistoryDto> birdCageHistories;
    private List<BirdReproductionDto> birdReproductions;
}
