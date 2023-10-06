package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDto {
    private Long id;
    private String name;
    private List<MealDto> meals = new ArrayList<>();
}
