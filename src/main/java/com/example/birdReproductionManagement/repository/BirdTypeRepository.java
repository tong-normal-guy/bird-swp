package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.BirdType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdTypeRepository extends JpaRepository<BirdType, Long> {
    BirdType findByName(String name);
}
