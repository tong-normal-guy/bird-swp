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
    @Column(name = "hatch_date")
    private Date hatchDate;
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
    @Column(name = "image")
    private String image;
    @Column(name = "feather_color", columnDefinition = "nvarchar(255)")
    private String featherColor;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "father_Id")
    private Long fatherId;
    @Column(name = "mother_Id")
    private Long motherId;

    @ManyToOne
    @JoinColumn(name = "birdtype_id", nullable = false)
    private BirdType birdType;

//    @OneToMany(mappedBy = "father", cascade = CascadeType.REMOVE)

//    @ManyToOne
//    @JoinColumn(name = "father_id", nullable = false)
//    private Bird father;
//    @OneToMany(mappedBy = "mother", cascade = CascadeType.REMOVE)
//    private List<Bird> birdListOfMother = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "mother_id", nullable = false)
//    private Bird mother;

    @ManyToOne
    @JoinColumn(name = "cage_id")
    private Cage cage;

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdCageHistory> birdCageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdReproduction> birdReproductions = new ArrayList<>();
}
