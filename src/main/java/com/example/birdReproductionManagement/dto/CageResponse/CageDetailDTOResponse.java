package com.example.birdReproductionManagement.dto.CageResponse;

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
    private Long cageID;

    private Reproduction4CageDetailDTOResponse reproduction4CageDetailDTOResponse;
    private List<Bird4CageDetailDTOResponse> bird4CageDetailDTO;
    private User4CageDetailDTOResponse user4CageDetailDTOResponse;


}
