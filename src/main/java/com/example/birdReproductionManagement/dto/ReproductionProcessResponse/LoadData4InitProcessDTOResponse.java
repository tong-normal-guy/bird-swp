package com.example.birdReproductionManagement.dto.ReproductionProcessResponse;

import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessInitDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoadData4InitProcessDTOResponse {
    // cage useable
    private List<CageDTO> cage;
    private List<BirdType4ProcessInitDTOResponse> birdType;
}
