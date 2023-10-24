package com.example.birdReproductionManagement.dto.BirdTypeResponse;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.MutationBirdListDTO;
import com.example.birdReproductionManagement.dto.NormalBirdListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdType4ProcessInitDTOResponse {
    private String typeId;
    private String name;
    private Long incubate;
    private Long chick;
    private Long swingBranch;
    private String lifeExpectancy;
    private List<Bird4ProcessDTOResponse> hen;
    private List<Bird4ProcessDTOResponse> cock;
}
