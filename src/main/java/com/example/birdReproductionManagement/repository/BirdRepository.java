package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BirdRepository extends JpaRepository<Bird, Long> {

    String birdAgeRange = "Trưởng Thành";
    List<Bird> findByCage_Id(Long id);
    Bird findByCageId(Long cageId);
    List<Bird> findBySexIs(Sex sex);

    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE (rp.isDone = true OR rp IS NULL) " +
            "AND b.sex = :sex " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng Thành'")
    List<Bird> findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);

    @Query("SELECT DISTINCT b FROM Bird b " +
            "JOIN b.birdReproductions br " +
            "JOIN br.reproductionProcess rp " +
            "WHERE rp.isDone = true " +
            "AND b.sex = :sex " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng Thành'" +
            "ORDER BY b.mutationRate DESC")
    List<Bird> findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdTypeSortedByMutationRateDesc(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);

    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE (rp.isDone = true OR rp IS NULL) " +
            "AND b.sex = :sex  " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng Thành'" )
           // "AND br.reproductionRole = :reproductionRole")
    List<Bird> findParents(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);


    List<Bird> findAllByFatherId(Long fatherId);
    List<Bird> findAllByMotherId(Long motherId);



}
