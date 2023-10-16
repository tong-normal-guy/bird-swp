package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdReproductionDto {
    private Long id;
    private BirdDto bird;
    private Date actEggHatchDate;
    private Date actSwingBranch;
    private Date actAdultBirdDate;
    private ReproductionProcessDto reproductionProcess;
    private String reproductionRole;
}
