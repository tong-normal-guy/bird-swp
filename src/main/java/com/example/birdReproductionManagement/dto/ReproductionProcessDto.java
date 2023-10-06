package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.BirdReproduction;
import com.example.birdReproductionManagement.model.Cage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReproductionProcessDto {
    private Long id;
    private Date pairingDate;
    private Date eggLaidDate;
    private Date expEggHatchDate;
    private Date actEggHatchDate;
    private Date expSwingBranch;
    private Date actSwingBranch;
    private Date expAdultBirdDate;
    private Date actAdultBirdDate;
    private Integer totalEgg;
    private String stage;
    private Integer failEgg;
    private Cage cage;
    private List<BirdReproductionDto> birdReproductions;
}
