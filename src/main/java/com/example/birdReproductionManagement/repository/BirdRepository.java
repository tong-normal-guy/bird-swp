package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BirdRepository extends JpaRepository<Bird, Long> {

    String birdAgeRange = "Trưởng thành";
    List<Bird> findByCage_Id(Long id);
    Bird findByCageId(Long cageId);
    List<Bird> findBySexIs(Sex sex);
    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE (rp.id = NULL) " +
            "AND b.sex = :sex " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng thành'")
    List<Bird> findBirdsWhereSexIsAliveAndBirdType(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);
    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE (rp.isDone = true OR rp IS NULL) " +
            "AND b.sex = :sex " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng thành'")
    List<Bird> findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdType(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);

    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE (rp.isDone = true OR rp.id IS NULL) " +
            "AND b.sex = :sex " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng thành' " +
            "ORDER BY b.mutationRate DESC")
    List<Bird> findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdTypeSortedByMutationRateDesc(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);

    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE (rp.isDone = true OR rp IS NULL) " +
            "AND b.sex = :sex  " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng thành'" )
           // "AND br.reproductionRole = :reproductionRole")
    List<Bird> findParents(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);


    List<Bird> findAllByFatherId(Long fatherId);
    List<Bird> findAllByMotherId(Long motherId);
//    Optional<Bird> findByCageEmpty();

}
