package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.BirdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BirdTypeRepository extends JpaRepository<BirdType, Long> {
    BirdType findByName(String name);
    @Query("SELECT a FROM BirdType a WHERE a.name LIKE CONCAT('%', :query, '%')")
    List<BirdType> searchByName(String query);
}
