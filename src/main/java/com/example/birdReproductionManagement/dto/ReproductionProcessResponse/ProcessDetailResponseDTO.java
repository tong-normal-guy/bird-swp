package com.example.birdReproductionManagement.dto.ReproductionProcessResponse;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdReForProcessDetailResponseDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessDetailResponseDTO {
    private String processId;
    private List<BirdReForProcessDetailResponseDTO> eggsList;
}
