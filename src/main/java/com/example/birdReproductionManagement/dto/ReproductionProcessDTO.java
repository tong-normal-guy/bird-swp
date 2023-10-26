package com.example.birdReproductionManagement.dto;

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
public class ReproductionProcessDTO {
    private String processId;
    private Date pairingDate;
    private Integer totalEgg;
    private String stage;
    private Integer failEgg;
    private Boolean isDone;
    private CageDTO cage;
    private String cageId;
    private BirdReproductionDTO cockReproduction;
    private BirdReproductionDTO henReproduction;
//    private List<BirdReproductionDto> birdReproductions;
}
