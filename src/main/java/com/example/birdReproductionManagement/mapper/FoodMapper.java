package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.FoodDto;
import com.example.birdReproductionManagement.model.Food;

import java.util.stream.Collectors;

public class FoodMapper {
    public static Food mapToFood(FoodDto foodDto){
        return Food.builder()
                .id(foodDto.getId())
                .name(foodDto.getName())
//                .meals(foodDto.getMeals().stream().map(MealMapper::mapToMeal).collect(Collectors.toList()))
                .build();
    }

    public static FoodDto mapToFoodDto(Food food){
        return FoodDto.builder()
                .id(food.getId())
                .name(food.getName())
//                .meals(food.getMeals().stream().map(MealMapper::mapToMealDto).collect(Collectors.toList()))
                .build();
    }
}
