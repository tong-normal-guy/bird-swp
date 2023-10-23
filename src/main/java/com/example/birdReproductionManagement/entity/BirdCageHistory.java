package com.example.birdReproductionManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "birdcage_history")
public class BirdCageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "impl_date")
    private Date implDate;

    @ManyToOne
    @JoinColumn(name = "bird_id", nullable = false)
    private Bird bird;

    @ManyToOne
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;
}
