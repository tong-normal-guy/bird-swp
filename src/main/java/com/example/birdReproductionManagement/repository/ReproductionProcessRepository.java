package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReproductionProcessRepository extends JpaRepository<ReproductionProcess, Long> {
    Optional<ReproductionProcess> findByIsDoneFalseAndCage_Id(Long cage_id);
    ReproductionProcess findByIsDoneFalseAndCage(Cage cage);
}
