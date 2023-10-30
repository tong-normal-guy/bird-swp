package com.example.birdReproductionManagement.dto.DashboardResponse;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EggInWeekDTOResponse {
    private List<EggPerDayDTOResponse> perDay;
}
