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
    private Boolean sex;
    private Date hatchDate;
    private String ageRange;
    private String mutation;
    private String image;
    private Long weight;

    @ManyToOne
    @JoinColumn(name = "birdtype_id", nullable = false)
    private BirdType birdType;

    @OneToMany(mappedBy = "father", cascade = CascadeType.REMOVE)
    private List<Bird> birdListOfFather = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "father_id", nullable = false)
    private Bird father;

    @OneToMany(mappedBy = "mother", cascade = CascadeType.REMOVE)
    private List<Bird> birdListOfMother = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "mother_id", nullable = false)
    private Bird mother;

    @ManyToOne
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdCageHistory> birdCageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "bird", cascade = CascadeType.REMOVE)
    private List<BirdReproduction> birdReproductions = new ArrayList<>();
}
