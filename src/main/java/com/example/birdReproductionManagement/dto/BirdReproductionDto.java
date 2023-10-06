package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.Bird;
import com.example.birdReproductionManagement.model.ReproductionProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdReproductionDto {
    private Long id;
    private Bird bird;
    private ReproductionProcess reproductionProcess;
    private Boolean isChild;
}
