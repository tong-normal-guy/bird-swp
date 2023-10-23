package com.example.birdReproductionManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cage")
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "location", columnDefinition = "nvarchar(255)")
    private String location;
    @Column(name = "available")
    private Boolean available;
    @Column(name = "bird_quantity")
    private Integer quantity;
//    @Column(name = "in_process", columnDefinition = "nvarchar(255)")
//    private Boolean inProcess;
    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<Bird> birdList = new ArrayList<>();

    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<BirdCageHistory> birdCageHistories = new ArrayList<>();

    @OneToMany(mappedBy = "cage", cascade = CascadeType.REMOVE)
    private List<ReproductionProcess> reproductionProcesses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
