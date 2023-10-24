package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.BirdCageHistoryDTO;
import com.example.birdReproductionManagement.entity.BirdCageHistory;

public class BirdCageHistoryMapper {
    public static BirdCageHistory mapToBirdCageHistory(BirdCageHistoryDTO birdCageHistoryDto){
        return BirdCageHistory.builder()
//                .id(birdCageHistoryDto.getId())
                .implDate(birdCageHistoryDto.getImplDate())
//                .bird(BirdMapper.mapToBird(birdCageHistoryDto.getBird()))
//                .cage(CageMapper.mapToCage(birdCageHistoryDto.getCage()))
                .build();
    }

    public static BirdCageHistoryDTO mapToBirdCageHistoryDto(BirdCageHistory birdCageHistory){
        return BirdCageHistoryDTO.builder()
                .historyId(String.valueOf(birdCageHistory.getId()))
                .implDate(birdCageHistory.getImplDate())
                .bird(BirdMapper.mapToBirdDto(birdCageHistory.getBird()))
                .cage(CageMapper.mapToCageDto(birdCageHistory.getCage()))
                .build();
    }

}
