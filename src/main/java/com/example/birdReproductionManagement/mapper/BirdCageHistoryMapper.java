package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdCageHistoryDto;
import com.example.birdReproductionManagement.model.BirdCageHistory;

public class BirdCageHistoryMapper {
    public static BirdCageHistory mapToBirdCageHistory(BirdCageHistoryDto birdCageHistoryDto){
        return BirdCageHistory.builder()
                .id(birdCageHistoryDto.getId())
                .implDate(birdCageHistoryDto.getImplDate())
                .bird(birdCageHistoryDto.getBird())
                .cage(birdCageHistoryDto.getCage())
                .build();
    }

    public static BirdCageHistoryDto mapToBirdCageHistoryDto(BirdCageHistory birdCageHistory){
        return BirdCageHistoryDto.builder()
                .id(birdCageHistory.getId())
                .implDate(birdCageHistory.getImplDate())
                .bird(birdCageHistory.getBird())
                .cage(birdCageHistory.getCage())
                .build();
    }

}
