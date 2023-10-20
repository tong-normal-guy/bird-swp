package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4CageDetailDTOResponse;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bird4CageDetailDTOResponse {
    private String birdId;
    private String birdSex;//
//    private String birdType;//
    private BirdType4CageDetailDTOResponse birdType;//
    private Float birdMutationRate;//
    private Float birdWeight;//
    private String birdImage;//

    //bird id?

}
