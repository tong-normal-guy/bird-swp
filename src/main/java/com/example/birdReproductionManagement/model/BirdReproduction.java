package com.example.birdReproductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

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
    @Column(name = "act_egg_hatch_date")
    private Date actEggHatchDate;
    @Column(name = "act_swing_branch_date")
    private Date actSwingBranch;
    @Column(name = "act_adult_bird_date")
    private Date actAdultBirdDate;
    @Column(name = "egg_type")
    private long eggType;
    @Column(name = "egg_status")
    private boolean eggStatus;
    @Column(name = "is_fail")
    private boolean isFail;
    @Column(name = "fail_date")
    private Date failDate;

    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private ReproductionProcess reproductionProcess;
    @Column(name = "is_child")
    private Boolean isChild;

    

}
