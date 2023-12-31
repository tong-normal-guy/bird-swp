package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BirdReproductionRepository extends JpaRepository<BirdReproduction, Long> {
    List<BirdReproduction> findAllByReproductionProcess_Id(Long id);
    List<BirdReproduction> findAllByBirdId(Long birdId);
    @Query("SELECT br FROM BirdReproduction br " +
            "WHERE br.reproductionProcess.id = :id " +
            "AND br.reproductionRole = 'EGG'")
    List<BirdReproduction> findAllEggsByReproductionProcessId(@Param("id") Long id);
    @Query("SELECT br FROM BirdReproduction br " +
            "WHERE br.reproductionProcess.id = :id " +
            "AND (br.reproductionRole = 'FATHER' OR br.reproductionRole = 'MOTHER')")
    List<BirdReproduction> findAllParentsByReproductionProcessId(@Param("id") Long id);
    BirdReproduction findByReproductionProcessIdAndReproductionRole(Long id, ReproductionRole reproductionRole);
    List<BirdReproduction> findAllByReproductionProcessIdAndReproductionRoleAndIsFailFalse(Long id, ReproductionRole reproductionRole);
    Boolean existsByBirdAndReproductionRoleNotAndReproductionRoleNotAndReproductionProcessIsDone
            (Bird bird, ReproductionRole reproductionRole1, ReproductionRole reproductionRole2, Boolean isDone);
    Boolean existsByBirdAndReproductionRoleNotAndReproductionRoleNot
            (Bird bird, ReproductionRole reproductionRole1, ReproductionRole reproductionRole2);
    Boolean existsByReproductionRoleAndReproductionProcessAndEggStatusEquals(ReproductionRole reproductionRole,
                                                                             ReproductionProcess reproductionProcess,
                                                                             String eggStatus);
    Boolean existsByBirdAndReproductionProcessIsDone(Bird bird, boolean isDone);
    Boolean existsByReproductionProcessAndReproductionRoleAndBirdAgeRangeNot(ReproductionProcess reproductionProcess,
                                                                          ReproductionRole reproductionRole,
                                                                          String ageRange);
    List<BirdReproduction> findByBirdAndReproductionRoleNot(Bird bird, ReproductionRole reproductionRole);
    BirdReproduction findByBirdAndReproductionRole(Bird bird, ReproductionRole reproductionRole);
//    List<BirdReproduction> findByReproductionProcessIdAndReproductionRoleEquals(Long id, ReproductionRole reproductionRole);

    List<BirdReproduction> findByReproductionProcessAndReproductionRole(ReproductionProcess reproductionProcess, ReproductionRole reproductionRole);
    Integer countAllByReproductionRoleAndIsFailIsFalse(ReproductionRole reproductionRole);
    @Query("SELECT COUNT(br) FROM BirdReproduction br " +
            "WHERE br.reproductionRole = 'EGG' " +
            "AND (br.eggStatus like 'Đang phát triển') " +
            "AND DATE(br.eggLaidDate) = :targetDate")
    Integer countByEggRoleAndInDevAndDate(@Param("targetDate") Date targetDate);

    @Query("SELECT COUNT(br) FROM BirdReproduction br " +
            "WHERE br.reproductionRole = 'EGG' " +
            "AND ( br.eggStatus like 'Hỏng') " +
            "AND DATE(br.eggLaidDate) = :targetDate")
    Integer countByEggRoleAndBrokenAndDate(@Param("targetDate") Date targetDate);
    @Query("SELECT br FROM BirdReproduction br " +
            "JOIN br.reproductionProcess rp " +
            "WHERE (br.reproductionRole = 'EGG' OR br.reproductionRole = 'CHILD') " +
            "AND br.eggLaidDate > CURRENT_DATE - 1 " +
            "AND rp.isDone = false")
    List<BirdReproduction> findEggAndChildReproductions();
    @Query("SELECT br FROM BirdReproduction br " +
            "JOIN br.reproductionProcess rp " +
            "WHERE (br.reproductionRole = 'EGG' OR br.reproductionRole = 'CHILD') " +
            "AND br.eggLaidDate >= :startDateTime " +
            "AND rp.isDone = false")
    List<BirdReproduction> findBirdReproductionsByConditions(@Param("startDateTime") Date startDateTime);
    @Query("SELECT COUNT(br) FROM BirdReproduction br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND br.reproductionRole = 'EGG' " +
            "AND br.eggStatus like :eggStatus ")
    Integer countByReproductionProcessAndReproductionRoleEGGAndEggStatus(
            @Param("reproductionProcess")ReproductionProcess reproductionProcess,
            @Param("eggStatus")String eggStatus);

    @Query("SELECT COUNT(br) FROM BirdReproduction br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND (br.reproductionRole = 'EGG' or br.reproductionRole = 'CHILD') ")
    Integer countByReproductionProcessAndReproductionRoleEGG(
            @Param("reproductionProcess")ReproductionProcess reproductionProcess);
    @Query("SELECT COUNT(br) FROM BirdReproduction br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "LEFT JOIN br.bird b " +
            "WHERE br.reproductionRole = 'CHILD' " +
            "AND br.reproductionProcess = :reproductionProcess ")
    Integer countByReproductionProcessAndReproductionRoleCHILD(
            @Param("reproductionProcess")ReproductionProcess reproductionProcess);
    @Query("SELECT br FROM BirdReproduction br " +
            "JOIN br.reproductionProcess rp " +
            "WHERE (br.reproductionRole = 'EGG' OR br.reproductionRole = 'CHILD') " +
            "AND rp.isDone = false")
    List<BirdReproduction> findEggOrChildReproductionsWithIsDoneFalse();
    @Query("SELECT br FROM BirdReproduction br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "LEFT JOIN br.bird b " +
            "LEFT JOIN b.cage c " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND b.cage = :cage " +
            "AND br.reproductionRole != 'EGG' ")
    List<BirdReproduction> findByReproductionRoleNotEGG(@Param("reproductionProcess")ReproductionProcess reproductionProcess,
                                                        @Param("cage")Cage cage);
    @Query("SELECT br FROM BirdReproduction br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "LEFT JOIN br.bird b " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND br.reproductionRole = 'EGG' ")
    List<BirdReproduction> findByReproductionRoleEGG(@Param("reproductionProcess")ReproductionProcess reproductionProcess);
}
