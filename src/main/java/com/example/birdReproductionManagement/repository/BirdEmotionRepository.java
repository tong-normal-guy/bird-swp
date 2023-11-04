package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdEmotion;
import com.example.birdReproductionManagement.entity.BirdEmotionId;
import com.example.birdReproductionManagement.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdEmotionRepository extends JpaRepository<BirdEmotion, BirdEmotionId> {
    List<BirdEmotion> findByCockAndEmotion(Bird bird, Emotion emotion);
}
