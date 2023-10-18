package com.example.birdReproductionManagement.entity;

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
    @Column(name = "pairing_date")
    private Date pairingDate;
    @Column(name = "exp_egg_hatch_date")
    private Date expEggHatchDate;
    @Column(name = "exp_swing_branch")
    private Date expSwingBranch;
    @Column(name = "exp_adult_bird_date")
    private Date expAdultBirdDate;
    @Column(name = "total_egg")
    private Integer totalEgg;
    @Column(name = "stage", columnDefinition = "nvarchar(255)")
    private String stage;
    @Column(name = "fail_egg")
    private Integer failEgg;
    @Column(name = "is_done")
    private Boolean isDone;
    @Column(name = "separate_date")
    private Date separateDate;
    @ManyToOne
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;
    @OneToMany(mappedBy = "reproductionProcess", cascade = CascadeType.REMOVE)
    private List<BirdReproduction> birdReproductions = new ArrayList<>();
}


