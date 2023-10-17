package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
