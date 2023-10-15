package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdRepository extends JpaRepository<Bird, Long> {
    List<Bird> findByCageId(Long cageId);

}
