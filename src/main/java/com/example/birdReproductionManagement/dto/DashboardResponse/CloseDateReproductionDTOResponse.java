package com.example.birdReproductionManagement.dto.DashboardResponse;

import com.example.birdReproductionManagement.entity.BirdReproduction;
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
    private String reproductionRole;
    private Date nextDate;
    private String desc;
}
