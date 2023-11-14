package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdReproductionForBirdDetailResponseDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdDetailReponseDTO {
    private String birdId;
    private String sex;
    private Date hatchDate;
    private Date swingBranchDate;
    private Date adultBirdDate;
    private String ageRange;
    private String mutation;
    private Float mutationRate;
    private Float superReproduct;
    private Boolean isAlive;
    private String status;
    private String image;
    private String featherColor;
    private Float weight;
    private String birdTypeName;
//    private BirdTypeDTO birdType;
//    private CageDTO cage;
    private String cageId;
    private String cage;
    private BirdReproductionForBirdDetailResponseDTO birdReproduction;
    private BirdForPedigreeResponseDTO father;
    private BirdForPedigreeResponseDTO mother;
    private List<DescendantResponseDTO> descendants;
}
