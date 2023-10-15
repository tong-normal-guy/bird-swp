package com.example.birdReproductionManagement.dto.BirdResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bird4CageDetailDTOResponse {
    private Long id;
    private Boolean isMale;
    private String birdType;
    private Date eggLaidDate;
    private String reproductionRole;
    private String eggStatus;
    private Date actEggHatchDate;
    private Float mutationRate;
    private Float weight;

}
