package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {
    private Long id;
    private String foodNorm;
    private BirdTypeDto birdType;
    private FoodDto food;
}
