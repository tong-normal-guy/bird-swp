package com.example.birdReproductionManagement.dto.BirdReproductionResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdReproductionForBirdDetailResponseDTO {
    private String reproductionId;
    private Date expEggHatchDate;
    private Date expSwingBranch;
    private Date expAdultBirdDate;
}
