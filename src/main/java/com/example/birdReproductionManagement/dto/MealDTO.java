package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDTO {
    private String mealId;
    private String food;
    private String foodNorm;
//    private BirdTypeDto birdType;
    private String birdTypeName;
}
