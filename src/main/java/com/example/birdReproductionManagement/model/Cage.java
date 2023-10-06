package com.example.birdReproductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cage")
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private Boolean available;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "cage", nullable = false)
    private CageType cageType;

    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<Bird> birdList = new ArrayList<>();

    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<BirdCageHistory> birdCageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<ReproductionProcess> reproductionProcesses = new ArrayList<>();

    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<WorkDivision> workDivisions = new ArrayList<>();

}
