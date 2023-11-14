package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
            "AND NOT EXISTS (" +
            "   SELECT 1 FROM BirdReproduction subBr " +
            "   JOIN ReproductionProcess subRp ON subBr.reproductionProcess.id = subRp.id " +
            "   WHERE subBr.bird.id = b.id AND subRp.isDone = false" +
            ") " +
            "AND b.sex = :sex " +
            "AND b.isAlive = true " +
            "AND b.birdType.id = :birdTypeId " +
            "AND b.ageRange = 'Trưởng thành' " +
            "ORDER BY b.mutationRate DESC")
    List<Bird> findBirdsWithConditions(@Param("sex") Sex sex, @Param("birdTypeId") Long birdTypeId);

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

    List<Bird> findByIsAliveTrue();
//    Optional<Bird> findByCageEmpty();
    @Query("SELECT DISTINCT b FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND br.reproductionRole = :reproductionRole")
    List<Bird> findByReproductionProcessAndReproductionRole(
            @Param("reproductionProcess")ReproductionProcess reproductionProcess,
            @Param("reproductionRole")ReproductionRole reproductionRole);

    @Query("SELECT COUNT(b) FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND br.reproductionRole = :reproductionRole ")
    Integer countBirdByReproductionProcessAndReproductionRole(@Param("reproductionProcess")ReproductionProcess reproductionProcess,
                                                                         @Param("reproductionRole")ReproductionRole reproductionRole);

    @Query("SELECT COUNT(b) FROM Bird b " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND br.reproductionRole = :reproductionRole " +
            "AND b.mutation IS NOT NULL ")
    Integer countBirdByMutationAndReproductionProcessAndReproductionRole(@Param("reproductionProcess")ReproductionProcess reproductionProcess,
                                                                         @Param("reproductionRole")ReproductionRole reproductionRole);

    @Query("SELECT COUNT(b) FROM Bird b " +
            "LEFT JOIN b.cage c " +
            "LEFT JOIN b.birdReproductions br " +
            "LEFT JOIN br.reproductionProcess rp " +
            "WHERE br.reproductionProcess = :reproductionProcess " +
            "AND b.cage = :cage " +
            "AND br.reproductionRole != 'EGG' ")
    Integer countByCageAndReproductionProcess(@Param("reproductionProcess")ReproductionProcess reproductionProcess,
                                              @Param("cage")Cage cage);


    @Query("SELECT COUNT(b) FROM Bird b " +
            "LEFT JOIN b.cage c " +
            "WHERE b.cage = :cage ")
    Integer countBirdByCage(@Param("cage") Cage cage);
}
