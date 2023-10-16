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
@Table(name = "bird")
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)", name = "sex")
    private Sex sex;
    @Column(name = "hatch_date")
    private Date hatchDate;
    @Column(name = "age_range", columnDefinition = "nvarchar(255)")
    private String ageRange;
    @Column(name = "mutation", columnDefinition = "nvarchar(255)")
    private String mutation;
    @Column(name = "mutation_rate")
    private Float mutationRate;
    @Column(name = "is_alive")
    private Boolean isAlive;
    @Column(name = "image")
    private String image;
    @Column(name = "feather_color", columnDefinition = "nvarchar(255)")
    private String featherColor;
    @Column(name = "weight")
    private Long weight;

    @ManyToOne
    @JoinColumn(name = "birdtype_id", nullable = false)
    private BirdType birdType;

//    @OneToMany(mappedBy = "father", cascade = CascadeType.REMOVE)
//    private List<Bird> birdListOfFather = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "father_id", nullable = false)
//    private Bird father;
//    @OneToMany(mappedBy = "mother", cascade = CascadeType.REMOVE)
//    private List<Bird> birdListOfMother = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "mother_id", nullable = false)
//    private Bird mother;

    @ManyToOne
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdCageHistory> birdCageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdReproduction> birdReproductions = new ArrayList<>();
}
