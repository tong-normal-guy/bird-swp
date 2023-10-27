package com.example.birdReproductionManagement.dto.BirdResponse;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bird4ProcessDTOResponse {
    private String birdId;
    private String sex;
    private Date hatchDate;
    private String ageRange;
    private String mutation;
    private Float mutationRate;
    private Float superReproduct;
    private Boolean isAlive;
    private String image;
    private String featherColor;
    private Float weight;
}
