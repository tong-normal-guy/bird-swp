package com.example.birdReproductionManagement.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    @Fetch(FetchMode.SELECT)
    private List<BirdReproduction> birdReproductions;
}


