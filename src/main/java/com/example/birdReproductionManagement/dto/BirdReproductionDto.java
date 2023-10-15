package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdReproductionDto {
    private Long id;
    private BirdDto bird;
    private Date eggLaidDate;
    private Date actEggHatchDate;
    private Date actSwingBranch;
    private Date actAdultBirdDate;
    private String eggType;
    private String eggStatus;
    private Boolean isFail;
    private Date failDate;
    private ReproductionProcessDto reproductionProcess;
    private String reproductionRole;
//    private Boolean isChild;
}
