package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdTypeDTO {
    private String typeId;
    private String name;
    private Long incubate;
    private Long chick;
    private Long swingBranch;
    private String lifeExpectancy;
    private String description;
//    private List<BirdDto> birdList;
//    private List<MealDto> meals;
}
