package com.example.birdReproductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long foodNorm;

    @ManyToOne
    @JoinColumn(name = "birdtype_id", nullable = false)
    private BirdType birdType;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

}
