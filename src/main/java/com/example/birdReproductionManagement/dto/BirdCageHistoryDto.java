package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
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
public class BirdCageHistoryDto {
    private Long id;
    private Date implDate;
    private BirdDto bird;
    private CageDto cage;
}
