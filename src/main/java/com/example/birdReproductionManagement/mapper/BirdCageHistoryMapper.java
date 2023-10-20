package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdCageHistoryDto;
import com.example.birdReproductionManagement.entity.BirdCageHistory;

public class BirdCageHistoryMapper {
    public static BirdCageHistory mapToBirdCageHistory(BirdCageHistoryDto birdCageHistoryDto){
        return BirdCageHistory.builder()
//                .id(birdCageHistoryDto.getId())
                .implDate(birdCageHistoryDto.getImplDate())
//                .bird(BirdMapper.mapToBird(birdCageHistoryDto.getBird()))
//                .cage(CageMapper.mapToCage(birdCageHistoryDto.getCage()))
                .build();
    }

    public static BirdCageHistoryDto mapToBirdCageHistoryDto(BirdCageHistory birdCageHistory){
        return BirdCageHistoryDto.builder()
                .id(String.valueOf(birdCageHistory.getId()))
                .implDate(birdCageHistory.getImplDate())
                .bird(BirdMapper.mapToBirdDto(birdCageHistory.getBird()))
                .cage(CageMapper.mapToCageDto(birdCageHistory.getCage()))
                .build();
    }

}
