package com.example.birdReproductionManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PairDTO {
    private Long cageId;
    private Long cockId;
    private Long henId;
}
