package com.example.birdReproductionManagement.dto.CageResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cage4ListDTO {
    private Long id;
    private String location;
    private int quantity;
}
