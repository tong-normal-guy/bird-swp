package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Cage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CageRepository extends JpaRepository<Cage, Long> {
    @Query("SELECT c FROM Cage c WHERE c.location LIKE 'B%'")
    List<Cage> findCagesWithLocationStartingWithB();
}
