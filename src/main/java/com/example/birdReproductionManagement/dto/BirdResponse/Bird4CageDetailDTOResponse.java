package com.example.birdReproductionManagement.dto.BirdResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bird4CageDetailDTOResponse {
    private Long birdId;
    private String birdSex;//
    private String birdType;//
    private Float birdMutationRate;//
    private Float birdWeight;//
    private String birdImage;//

    //bird id?

}
