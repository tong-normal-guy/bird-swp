package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.BirdReproduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
}
