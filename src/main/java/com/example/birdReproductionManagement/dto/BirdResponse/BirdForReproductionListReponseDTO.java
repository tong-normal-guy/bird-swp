package com.example.birdReproductionManagement.dto.BirdResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdForReproductionListReponseDTO {
    private String birdId;
    private Date actEggHatchDate;
    private Date actSwingBranchDate;
    private Date actAdultBirdDate;
}
