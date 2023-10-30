package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdForListResponseDTO {
    private String birdId;
    private String birdTypeName;
    private String sex;
//    private String cageId;
    private CageDTO cage;
    private Date hatchDate;
    private String ageRange;
    private String mutation;
    private Float mutationRate;
    private Float weight;
    private String featherColor;
    private String image;
    private Boolean isAlive;
    private String status;
}
