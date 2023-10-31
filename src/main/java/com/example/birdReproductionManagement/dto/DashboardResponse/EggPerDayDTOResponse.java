package com.example.birdReproductionManagement.dto.DashboardResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EggPerDayDTOResponse {
    private Date eggLaidDate;
    private int totalSucessEgg;
    private int totalFailEgg;


}
