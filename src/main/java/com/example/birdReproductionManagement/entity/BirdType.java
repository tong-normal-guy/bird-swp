package com.example.birdReproductionManagement.entity;

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
    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;
    @Column(name = "incubate_period")
    private Long incubate;
    @Column(name = "chick_period")
    private Long chick;
    @Column(name = "swingbranch_period")
    private Long swingBranch;
    @Column(name = "life_expectancy", columnDefinition = "nvarchar(255)")
    private String lifeExpectancy;
    @Column(name = "description", columnDefinition = "nvarchar(255)")
    private String description;

    @OneToMany(mappedBy = "birdType",cascade = CascadeType.REMOVE)
    private List<Bird> birdList = new ArrayList<>();

    @OneToMany(mappedBy = "birdType", cascade = CascadeType.REMOVE)
    private List<Meal> meals = new ArrayList<>();
}
