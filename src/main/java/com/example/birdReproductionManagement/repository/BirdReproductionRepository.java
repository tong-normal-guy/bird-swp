package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
    List<BirdReproduction> findAllByReproductionProcess_Id(Long id);
    BirdReproduction findByReproductionProcessIdAndReproductionRoleEquals(Long id, ReproductionRole reproductionRole);
}
