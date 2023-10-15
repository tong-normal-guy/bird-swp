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
@Table(name = "cagetype")
public class CageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;
    @Column(name = "size", columnDefinition = "nvarchar(255)")
    private String size;
    @Column(name = "description", columnDefinition = "nvarchar(255)")
    private String description;
    @OneToMany(mappedBy = "cageType", cascade = CascadeType.REMOVE)
    private List<Cage> cageList =  new ArrayList<>();
}
