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
@Table(name = "bird_reproduction")
public class BirdReproduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bird_id", nullable = false)
    private Bird bird;

    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private ReproductionProcess reproductionProcess;

    private Boolean isChild;

}
