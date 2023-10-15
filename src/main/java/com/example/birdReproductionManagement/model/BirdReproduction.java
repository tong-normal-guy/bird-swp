package com.example.birdReproductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bird_reproduction")
public class BirdReproduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bird_id", nullable = false)
    private Bird bird;
    @Column(name = "egglaid_date")
    private Date eggLaidDate;
    @Column(name = "act_egg_hatch_date")
    private Date actEggHatchDate;
    @Column(name = "act_swing_branch_date")
    private Date actSwingBranch;
    @Column(name = "act_adult_bird_date")
    private Date actAdultBirdDate;
    @Column(name = "egg_type")
    private String eggType;
    @Column(name = "egg_status")
    private String eggStatus;


    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private ReproductionProcess reproductionProcess;

    @Column(name = "reproduction_role")
    private ReproductionRole reproductionRole;


}
