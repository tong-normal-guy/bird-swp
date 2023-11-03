package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.BirdEmotion;
import com.example.birdReproductionManagement.entity.BirdEmotionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdEmotionRepository extends JpaRepository<BirdEmotion, BirdEmotionId> {
}
