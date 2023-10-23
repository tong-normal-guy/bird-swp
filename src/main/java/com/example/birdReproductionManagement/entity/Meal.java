package com.example.birdReproductionManagement.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "food_norm", columnDefinition = "nvarchar(255)")
    private String foodNorm;
    @Column(name = "food", columnDefinition = "nvarchar(255)")
    private String food;
    @ManyToOne
    @JoinColumn(name = "birdtype_id", nullable = false)
    private BirdType birdType;

}
