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
public class BirdDetailReponseDTO {
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
//    private BirdTypeDTO birdType;
//    private CageDTO cage;
    private String cageId;
    private String cage;
    private BirdForPedigreeResponseDTO father;
    private BirdForPedigreeResponseDTO mother;
    private List<DescendantResponseDTO> descendants;
}
