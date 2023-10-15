package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.CageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CageTypeRepository extends JpaRepository<CageType, Long> {
}
