package com.example.birdReproductionManagement.dto.BirdResponse;

import com.example.birdReproductionManagement.entity.Bird;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutationBirdListDTO {
    private List<Bird4ProcessDTOResponse> hen;
    private List<Bird4ProcessDTOResponse> cock;
}
