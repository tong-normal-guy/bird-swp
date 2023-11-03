package com.example.birdReproductionManagement.entity;

import lombok.*;

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
@Table(name = "bird")
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)", name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name = "age_range", columnDefinition = "nvarchar(255)")
    private String ageRange;
    @Column(name = "mutation_note", columnDefinition = "nvarchar(255)")
    private String mutation;
    @Column(name = "mutation_rate")
    private Float mutationRate;
    @Column(name = "super_reproduct")
    private Float superReproduct;
    @Column(name = "is_alive")
    private Boolean isAlive;
    @Column(name = "status", columnDefinition = "nvarchar(255)")
    private String status;
    @Column(name = "image")
    private String image;
    @Column(name = "feather_color", columnDefinition = "nvarchar(255)")
    private String featherColor;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "hatch_date")
    private Date hatchDate;
    @Column(name = "swing_branch_date")
    private Date swingBranchDate;
    @Column(name = "adult_bird_date")
    private Date adultBirdDate;
    @Column(name = "father_Id")
    private Long fatherId;
    @Column(name = "mother_Id")
    private Long motherId;

    @ManyToOne
    @JoinColumn(name = "birdtype_id", nullable = false)
    private BirdType birdType;

    @ManyToOne
    @JoinColumn(name = "cage_id")
    private Cage cage;

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdCageHistory> birdCageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdReproduction> birdReproductions = new ArrayList<>();

    @OneToMany(mappedBy = "cock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BirdEmotion> cocks;
    @OneToMany(mappedBy = "hen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BirdEmotion> hens;


}
