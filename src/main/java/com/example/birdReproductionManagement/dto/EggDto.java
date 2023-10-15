package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EggDto {
    private Integer number;
    private Date hatchDate;
    private Long processId;
//    private String type;
//    private String status;
//    private Date fertilityCheckDate;
//    private Long birdId;
}
