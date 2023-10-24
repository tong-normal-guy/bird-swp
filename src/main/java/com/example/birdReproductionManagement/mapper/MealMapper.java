package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.MealDTO;
import com.example.birdReproductionManagement.entity.Meal;

public class MealMapper {
    public static Meal mapToMeal(MealDTO mealDto){
        return Meal.builder()
//                .id(mealDto.getId())
                .food(mealDto.getFood())
                .foodNorm(mealDto.getFoodNorm())
//                .birdType(BirdTypeMapper.mapToBirdType(mealDto.getBirdType()))
//                .food(FoodMapper.mapToFood(mealDto.getFood()))
                .build();
    }

    public static MealDTO mapToMealDto(Meal meal){
        return MealDTO.builder()
                .mealId(String.valueOf(meal.getId()))
                .food(meal.getFood())
                .foodNorm(meal.getFoodNorm())
                .birdTypeName(meal.getBirdType().getName())
//                .food(FoodMapper.mapToFoodDto(meal.getFood()))
                .build();
    }
}
