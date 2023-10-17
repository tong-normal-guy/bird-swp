package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Cage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CageRepository extends JpaRepository<Cage, Long> {
}
