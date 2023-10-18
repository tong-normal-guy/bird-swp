package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Cage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CageRepository extends JpaRepository<Cage, Long> {
    List<Cage> findByLocationContains(String location);
}
