package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
    List<BirdReproduction> findAllByReproductionProcess_Id(Long id);
    BirdReproduction findByReproductionProcessIdAndReproductionRole(Long id, ReproductionRole reproductionRole);
    Boolean existsByBirdAndReproductionRoleNotAndReproductionRoleNotAndReproductionProcessIsDone
            (Bird bird, ReproductionRole reproductionRole1, ReproductionRole reproductionRole2, Boolean isDone);
    List<BirdReproduction> findByBirdAndReproductionRoleNot(Bird bird, ReproductionRole reproductionRole);
//    List<BirdReproduction> findByReproductionProcessIdAndReproductionRoleEquals(Long id, ReproductionRole reproductionRole);
    List<BirdReproduction> findByReproductionProcessAndReproductionRole(ReproductionProcess reproductionProcess, ReproductionRole reproductionRole);
}
