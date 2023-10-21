package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdRepository extends JpaRepository<Bird, Long> {
    List<Bird> findByCage_Id(Long id);
    List<Bird> findBySexIs(Sex sex);
}
