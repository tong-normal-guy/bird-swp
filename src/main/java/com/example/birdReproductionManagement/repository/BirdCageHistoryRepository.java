package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.BirdCageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdCageHistoryRepository extends JpaRepository<BirdCageHistory, Long> {
}
