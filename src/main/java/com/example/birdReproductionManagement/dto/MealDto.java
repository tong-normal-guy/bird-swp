package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.BirdType;
import com.example.birdReproductionManagement.model.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
