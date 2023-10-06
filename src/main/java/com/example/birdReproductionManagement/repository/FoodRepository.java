package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
