package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.BirdCageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdCageHistoryRepository extends JpaRepository<BirdCageHistory, Long> {
}
