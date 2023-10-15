package com.example.birdReproductionManagement.dto.ReproductionProcessResponse;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Reproduction4CageDetailDTOResponse {
    private Long id;
    private Date pairingDate;
    private String stage;
    private Integer failEgg;
    private Integer totalEgg;
    private Date expEggHatchDate;
    private Date expSwingBranch;
    private Date expAdultBirdDate;
}
