package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.Bird;
import com.example.birdReproductionManagement.model.Cage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdCageHistoryDto {
    private Long id;
    private Date implDate;
    private Bird bird;
    private Cage cage;
}
