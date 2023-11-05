package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.entity.BirdEmotion;
import com.example.birdReproductionManagement.entity.BirdEmotionId;

public class BirdEmotionMapper {
    public static PairDTO mapToPairDTO(BirdEmotion birdEmotion){
        return PairDTO.builder()
                .cockId(String.valueOf(birdEmotion.getCock().getId()))
                .henId(String.valueOf(birdEmotion.getHen().getId()))
                .build();
    }
}
