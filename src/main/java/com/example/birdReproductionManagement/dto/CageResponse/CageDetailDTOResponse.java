package com.example.birdReproductionManagement.dto.CageResponse;

import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CageDetailDTOResponse {
    private String cageId;
    private String location;
    private int quantity;
    private Reproduction4CageDetailDTOResponse reproductionProcess;
    private List<BirdRe4CageDetailDTOResponse> birdReproduction;
    private User4CageDetailDTOResponse user;


}
