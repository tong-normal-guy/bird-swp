package com.example.birdReproductionManagement.dto.BirdTypeResponse;

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
public class BirdType4ProcessDTOResponse {
    private String typeId;
    private String name;
    private Long incubate;
    private Long chick;
    private Long swingBranch;
    private String lifeExpectancy;
    private MutationBirdListDTO mutationBirdList;
    private NormalBirdListDTO normalBirdList;
    private SuperReproductDTO superReproductDTO;
}
