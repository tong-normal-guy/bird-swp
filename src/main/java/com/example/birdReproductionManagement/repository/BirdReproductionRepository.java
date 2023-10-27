package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
    List<BirdReproduction> findAllByReproductionProcess_Id(Long id);
    List<BirdReproduction> findAllByBirdId(Long birdId);
    @Query("SELECT br FROM BirdReproduction br WHERE br.reproductionProcess.id = :id AND br.reproductionRole = 'EGG'")
    List<BirdReproduction> findAllEggsByReproductionProcessId(@Param("id") Long id);
    @Query("SELECT br FROM BirdReproduction br WHERE br.reproductionProcess.id = :id AND (br.reproductionRole = 'FATHER' OR br.reproductionRole = 'MOTHER')")
    List<BirdReproduction> findAllParentsByReproductionProcessId(@Param("id") Long id);
    BirdReproduction findByReproductionProcessIdAndReproductionRole(Long id, ReproductionRole reproductionRole);
    Boolean existsByBirdAndReproductionRoleNotAndReproductionRoleNotAndReproductionProcessIsDone
            (Bird bird, ReproductionRole reproductionRole1, ReproductionRole reproductionRole2, Boolean isDone);
    Boolean existsByReproductionRoleAndReproductionProcessAndEggStatusEquals(ReproductionRole reproductionRole, ReproductionProcess reproductionProcess, String eggStatus);
    List<BirdReproduction> findByBirdAndReproductionRoleNot(Bird bird, ReproductionRole reproductionRole);
    BirdReproduction findByBirdAndReproductionRole(Bird bird, ReproductionRole reproductionRole);
//    List<BirdReproduction> findByReproductionProcessIdAndReproductionRoleEquals(Long id, ReproductionRole reproductionRole);
    List<BirdReproduction> findByReproductionProcessAndReproductionRole(ReproductionProcess reproductionProcess, ReproductionRole reproductionRole);
}
