package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird, Long> {

}
