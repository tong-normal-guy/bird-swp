package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdReproductionDTO {
    private String reproductionId;
    private BirdDTO bird;
    private String birdId;
    private Date eggLaidDate;
//    private Date actEggHatchDate;
//    private Date actSwingBranch;
//    private Date actAdultBirdDate;
    private Date expEggHatchDate;
    private Date expSwingBranchDate;
    private Date expAdultBirdDate;
    private String eggType;
    private String eggStatus;
    private boolean isFail;
    private Date failDate;
    private ReproductionProcessDTO reproductionProcess;
    private String reproductionProcessId;
    private String reproductionRole;
    private Boolean isMoved;
}
