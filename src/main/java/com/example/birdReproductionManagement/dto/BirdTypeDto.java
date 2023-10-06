package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.Bird;
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
public class BirdTypeDto {
    private Long id;
    private String name;
    private Long incubate;
    private Long chick;
    private Long swingBranch;
    private String description;
    private List<BirdDto> birdList;
    private List<MealDto> meals;
}
