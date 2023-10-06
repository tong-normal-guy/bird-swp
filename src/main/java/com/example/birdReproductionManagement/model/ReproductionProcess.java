package com.example.birdReproductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reproduction_process")
public class ReproductionProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;

    @OneToMany(mappedBy = "reproductionProcess", cascade = CascadeType.REMOVE)
    private List<BirdReproduction> birdReproductions = new ArrayList<>();
}


