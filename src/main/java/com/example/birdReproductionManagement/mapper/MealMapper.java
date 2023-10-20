package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.MealDto;
import com.example.birdReproductionManagement.entity.Meal;

public class MealMapper {
    public static Meal mapToMeal(MealDto mealDto){
        return Meal.builder()
//                .id(mealDto.getId())
                .food(mealDto.getFood())
                .foodNorm(mealDto.getFoodNorm())
//                .birdType(BirdTypeMapper.mapToBirdType(mealDto.getBirdType()))
//                .food(FoodMapper.mapToFood(mealDto.getFood()))
                .build();
    }

    public static MealDto mapToMealDto(Meal meal){
        return MealDto.builder()
                .mealId(String.valueOf(meal.getId()))
                .food(meal.getFood())
                .foodNorm(meal.getFoodNorm())
                .birdTypeName(meal.getBirdType().getName())
//                .food(FoodMapper.mapToFoodDto(meal.getFood()))
                .build();
    }
}
