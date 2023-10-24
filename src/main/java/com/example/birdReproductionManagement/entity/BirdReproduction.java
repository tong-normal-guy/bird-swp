package com.example.birdReproductionManagement.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
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
    @JoinColumn(name = "bird_id")
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
    @Column(name = "exp_egg_hatch_date")
    private Date expEggHatchDate;
    @Column(name = "exp_swing_branch")
    private Date expSwingBranch;
    @Column(name = "exp_adult_bird_date")
    private Date expAdultBirdDate;
    @Column(name = "is_fail")
    private boolean isFail;
    @Column(name = "fail_date")
    private Date failDate;
    @Column(name = "reproduction_role")
    @Enumerated(EnumType.STRING)
    private ReproductionRole reproductionRole;

    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private ReproductionProcess reproductionProcess;

}
