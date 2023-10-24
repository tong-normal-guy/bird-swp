package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
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
    private String reproductionId;
    private BirdDto bird;
    private String birdId;
    private Date eggLaidDate;
    private Date actEggHatchDate;
    private Date actSwingBranch;
    private Date actAdultBirdDate;
    private Date expEggHatchDate;
    private Date expSwingBranch;
    private Date expAdultBirdDate;
    private String eggType;
    private String eggStatus;
    private boolean isFail;
    private Date failDate;
    private ReproductionProcessDto reproductionProcess;
    private String reproductionProcessId;
    private String reproductionRole;
}
