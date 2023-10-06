package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.WorkDivisionDto;
import com.example.birdReproductionManagement.model.WorkDivision;

public class WorkDivisionMapper {
    public static WorkDivision mapToWorkDivision(WorkDivisionDto workDivisionDto){
        return WorkDivision.builder()
                .id(workDivisionDto.getId())
                .createDate(workDivisionDto.getCreateDate())
                .workDate(workDivisionDto.getWorkDate())
                .report(workDivisionDto.getReport())
                .user(workDivisionDto.getUser())
                .cage(workDivisionDto.getCage())
                .build();
    }

    public static WorkDivisionDto mapToWorkDivisionDto(WorkDivision workDivision){
        return WorkDivisionDto.builder()
                .id(workDivision.getId())
                .createDate(workDivision.getCreateDate())
                .workDate(workDivision.getWorkDate())
                .report(workDivision.getReport())
                .user(workDivision.getUser())
                .cage(workDivision.getCage())
                .build();
    }
}
