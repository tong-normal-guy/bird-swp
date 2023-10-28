package com.example.birdReproductionManagement.dto.BirdResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdForPedigreeResponseDTO {
    private String birdId;
    private String sex;
    private Date hatchDate;
    private BirdForPedigreeResponseDTO father;
    private BirdForPedigreeResponseDTO mother;
}
