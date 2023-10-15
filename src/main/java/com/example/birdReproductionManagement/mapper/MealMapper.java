package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.MealDto;
import com.example.birdReproductionManagement.entity.Meal;

public class MealMapper {
    public static Meal mapToMeal(MealDto mealDto){
        return Meal.builder()
                .id(mealDto.getId())
                .foodNorm(mealDto.getFoodNorm())
                .birdType(BirdTypeMapper.mapToBirdType(mealDto.getBirdType()))
                .food(FoodMapper.mapToFood(mealDto.getFood()))
                .build();
    }

    public static MealDto mapToMealDto(Meal meal){
        return MealDto.builder()
                .id(meal.getId())
                .foodNorm(meal.getFoodNorm())
                .birdType(BirdTypeMapper.mapToBirdTypeDto(meal.getBirdType()))
                .food(FoodMapper.mapToFoodDto(meal.getFood()))
                .build();
    }
}
