package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.ReproductionProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReproductionProcessRepository extends JpaRepository<ReproductionProcess, Long> {
}
