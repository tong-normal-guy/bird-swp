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
    private String id;
    private BirdDto bird;
    private Date eggLaidDate;
    private Date actEggHatchDate;
    private Date actSwingBranch;
    private Date actAdultBirdDate;
    private String eggType;
    private String eggStatus;
    private boolean isFail;
    private Date failDate;
    private ReproductionProcessDto reproductionProcess;
    private Long reproductionProcessId;
    private String reproductionRole;
}
