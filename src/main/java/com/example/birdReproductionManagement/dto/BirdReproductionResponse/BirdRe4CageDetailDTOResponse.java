package com.example.birdReproductionManagement.dto.BirdReproductionResponse;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdRe4CageDetailDTOResponse {
    private String reproductionId;
    private Date eggLaidDate;
    private String reproductionRole;
    private String eggStatus;
    private Date actEggHatchDate;
    private Date expEggHatchDate ;
    private Date expSwingBranch;
    private Date expAdultBirdDate;
    private Bird4CageDetailDTOResponse bird;
}
