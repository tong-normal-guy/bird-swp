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
//    private Date actEggHatchDate;
//    private Date actSwingBranch;
//    private Date actAdultBirdDate;
    private Date expEggHatchDate;
    private Date expSwingBranchDate;
    private Date expAdultBirdDate;
    private String eggType;
    private String eggStatus;
    private boolean isFail;
    private Date failDate;
    private Bird4CageDetailDTOResponse bird;
}
