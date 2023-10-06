package com.example.birdReproductionManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "birdcage_history")
public class BirdCageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date implDate;

    @ManyToOne
    @JoinColumn(name = "bird_id", nullable = false)
    private Bird bird;

    @ManyToOne
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;
}
