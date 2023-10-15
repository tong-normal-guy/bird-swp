package com.example.birdReproductionManagement.dto;

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
public class ReproductionProcessDto {
    private Long id;
    private Date pairingDate;
    private Date eggLaidDate;
    private Date expEggHatchDate;
//    private Date actEggHatchDate;
    private Date expSwingBranch;
//    private Date actSwingBranch;
    private Date expAdultBirdDate;
//    private Date actAdultBirdDate;
    private Integer totalEgg;
    private String stage;
    private Integer failEgg;
    private Boolean isDone;
    private CageDto cage;
    private Long cageId;
//    private List<BirdReproductionDto> birdReproductions;
}
