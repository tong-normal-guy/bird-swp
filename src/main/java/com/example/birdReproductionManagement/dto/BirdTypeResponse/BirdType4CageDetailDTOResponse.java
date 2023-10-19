package com.example.birdReproductionManagement.dto.BirdTypeResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdType4CageDetailDTOResponse {
    private String typeId;
    private String name;
    private Long incubate;
    private Long chick;
    private Long swingBranch;
    private String lifeExpectancy;
}
