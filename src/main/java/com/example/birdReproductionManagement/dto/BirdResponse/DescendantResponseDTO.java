package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescendantResponseDTO {
    private String birdId;
    private String generation;
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
    private String cageId;
}
