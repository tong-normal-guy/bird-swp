package com.example.birdReproductionManagement.dto.CageResponse;

import com.example.birdReproductionManagement.dto.CageTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CageDto {
    private Long id;
    private String location;
    private Boolean available;
    private Integer quantity;
    private Boolean inProcess;
    private CageTypeDto cageType;
    private Long cageTypeId;
//    private List<BirdDto> birdList;
//    private List<BirdCageHistoryDto> birdCageHistories;
//    private List<ReproductionProcessDto> reproductionProcesses;
//    private List<WorkDivisionDto> workDivisions;
}
