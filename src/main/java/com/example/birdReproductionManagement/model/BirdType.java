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
@Table(name = "birdtype")
public class BirdType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long incubate;
    private Long chick;
    private Long swingBranch;
    private String description;

    @OneToMany(mappedBy = "birdType",cascade = CascadeType.REMOVE)
    private List<Bird> birdList = new ArrayList<>();

    @OneToMany(mappedBy = "birdType", cascade = CascadeType.REMOVE)
    private List<Meal> meals = new ArrayList<>();
}
