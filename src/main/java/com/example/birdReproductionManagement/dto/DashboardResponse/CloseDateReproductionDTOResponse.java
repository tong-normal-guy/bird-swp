package com.example.birdReproductionManagement.dto.DashboardResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseDateReproductionDTOResponse {
    private String cageId;
    private Date pairingDate;
}
