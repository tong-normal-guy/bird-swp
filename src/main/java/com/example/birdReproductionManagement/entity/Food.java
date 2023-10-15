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
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<Meal> meals = new ArrayList<>();
}
