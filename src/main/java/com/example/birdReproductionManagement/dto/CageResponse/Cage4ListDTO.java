package com.example.birdReproductionManagement.dto.CageResponse;

import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Process4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.UserDTO;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cage4ListDTO {
    private String cageId;
    private String location;
    private boolean available;
    private int quantity;
    private UserDTO user;
//    private Process4CageDetailDTOResponse reproductionProces;
//    private List<Bird4CageDetailDTOResponse> bird;
//    private List<BirdRe4CageDetailDTOResponse> birdReproduction;
//    private User4CageDetailDTOResponse user4CageDetailDTOResponse;
}
