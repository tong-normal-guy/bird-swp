package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.BirdReproduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
    @Query("SELECT a FROM BirdReproduction a WHERE a.reproductionProcess.id = :processId and a.reproductionRole = 'Father'")
    BirdReproduction findFatherInProcess(Long processId);
}
