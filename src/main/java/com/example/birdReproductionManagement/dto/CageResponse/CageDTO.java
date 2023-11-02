package com.example.birdReproductionManagement.dto.CageResponse;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CageDTO {
    private String cageId;
    private String location;
//    private String code;
    private Boolean available;
    private Integer quantity;
//    private Boolean inProcess;
//    private CageTypeDto cageType;
//    private Long cageTypeId;
    private UserDTO user;
    private String userId;
    private List<BirdDTO> bird;
//    private List<BirdCageHistoryDto> birdCageHistories;
//    private List<ReproductionProcessDto> reproductionProcesses;
//    private List<WorkDivisionDto> workDivisions;
}
