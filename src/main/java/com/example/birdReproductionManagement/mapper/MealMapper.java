package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.MealDto;
import com.example.birdReproductionManagement.model.Meal;

public class MealMapper {
    public static Meal mapToMeal(MealDto mealDto){
        return Meal.builder()
                .id(mealDto.getId())
                .foodNorm(mealDto.getFoodNorm())
                .birdType(mealDto.getBirdType())
                .food(mealDto.getFood())
                .build();
    }

    public static MealDto mapToMealDto(Meal meal){
        return MealDto.builder()
                .id(meal.getId())
                .foodNorm(meal.getFoodNorm())
                .birdType(meal.getBirdType())
                .food(meal.getFood())
                .build();
    }
}
