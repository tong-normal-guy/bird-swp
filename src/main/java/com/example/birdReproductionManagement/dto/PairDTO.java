package com.example.birdReproductionManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PairDTO {
    private String cageId;
    private String cockId;
    private String henId;
}
