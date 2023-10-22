package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4ProcessDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NormalBirdListDTO {
    private List<Bird4ProcessDTOResponse> hen;
    private List<Bird4ProcessDTOResponse> cock;
}
