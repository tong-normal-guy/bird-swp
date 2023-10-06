package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CageDto {
    private Long id;
    private String location;
    private Boolean available;
    private Integer quantity;
    private CageType cageType;
    private List<BirdDto> birdList;
    private List<BirdCageHistoryDto> birdCageHistories;
    private List<ReproductionProcessDto> reproductionProcesses;
    private List<WorkDivisionDto> workDivisions;
}
