package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CageTypeDto {
    private Long id;
    private String name;
    private String size;
    private String description;
//    private List<CageDto> cageList;
}
