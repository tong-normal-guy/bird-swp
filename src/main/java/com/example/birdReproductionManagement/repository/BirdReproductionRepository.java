package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.BirdReproduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
    List<BirdReproduction> findAllByReproductionProcess_Id(Long id);
}
