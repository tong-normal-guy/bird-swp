package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReproductionProcessRepository extends JpaRepository<ReproductionProcess, Long> {
    Optional<ReproductionProcess> findByIsDoneFalseAndCage_Id(Long cage_id);
    ReproductionProcess findByIsDoneFalseAndCage(Cage cage);
    List<ReproductionProcess> findAllByIsDoneFalse();
    Integer countAllByIsDoneFalse();
    @Query("SELECT DISTINCT p FROM ReproductionProcess p " +
            "LEFT JOIN p.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.bird = :bird " +
            "AND br.reproductionRole != :reproductionRole")
    List<ReproductionProcess> findByBirdAndReproductionRoleNot(@Param("bird")Bird bird, @Param("reproductionRole")ReproductionRole reproductionRole);

    @Query("SELECT DISTINCT p FROM ReproductionProcess p " +
            "LEFT JOIN p.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.bird = :bird " +
            "AND (br.reproductionRole = 'FATHER' OR br.reproductionRole = 'MOTHER') ")
    List<ReproductionProcess> findParentReproductionProcessByBird(@Param("bird")Bird bird);
    @Query("SELECT rp FROM ReproductionProcess rp " +
            "JOIN rp.birdReproductions br " +
            "WHERE br.expEggHatchDate = :currentDate " +
            "AND br.eggLaidDate = (SELECT MAX(br2.eggLaidDate) FROM BirdReproduction br2 WHERE br2.reproductionProcess = rp)")
    List<ReproductionProcess> findReproductionProcessesWithMatchingDates(@Param("currentDate") Date currentDate);
}
