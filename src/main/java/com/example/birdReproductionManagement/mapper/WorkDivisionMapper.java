package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.WorkDivisionDto;
import com.example.birdReproductionManagement.entity.WorkDivision;

public class WorkDivisionMapper {
    public static WorkDivision mapToWorkDivision(WorkDivisionDto workDivisionDto){
        return WorkDivision.builder()
                .id(workDivisionDto.getId())
                .createDate(workDivisionDto.getCreateDate())
                .workDate(workDivisionDto.getWorkDate())
                .isAttended(workDivisionDto.getIsAttended())
                .report(workDivisionDto.getReport())
                .user(UserEntityMapper.mapToUserEntity(workDivisionDto.getUser()))
                .cage(CageMapper.mapToCage(workDivisionDto.getCage()))
                .build();
    }

    public static WorkDivisionDto mapToWorkDivisionDto(WorkDivision workDivision){
        return WorkDivisionDto.builder()
                .id(workDivision.getId())
                .createDate(workDivision.getCreateDate())
                .workDate(workDivision.getWorkDate())
                .isAttended(workDivision.getIsAttended())
                .report(workDivision.getReport())
                .user(UserEntityMapper.mapToUserEntityDto(workDivision.getUser()))
                .cage(CageMapper.mapToCageDto(workDivision.getCage()))
                .build();
    }
}
