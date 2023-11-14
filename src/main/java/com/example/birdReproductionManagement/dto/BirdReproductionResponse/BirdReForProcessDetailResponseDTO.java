package com.example.birdReproductionManagement.dto.BirdReproductionResponse;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdForReproductionListReponseDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdReForProcessDetailResponseDTO {
    private String reproductionId;
    private BirdForReproductionListReponseDTO bird;
//    private String birdId;
    private Date eggLaidDate;
    //    private Date actEggHatchDate;
//    private Date actSwingBranch;
//    private Date actAdultBirdDate;
//    private Date expEggHatchDate;
//    private Date expSwingBranchDate;
//    private Date expAdultBirdDate;
//    private String eggType;
    private String eggStatus;
//    private boolean isFail;
//    private Date failDate;
//    private ReproductionProcessDTO reproductionProcess;
//    private String reproductionProcessId;
//    private String reproductionRole;
//    private Boolean isMoved;
}
