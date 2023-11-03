package com.example.birdReproductionManagement.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class BirdEmotionId implements Serializable {
    @Column(name = "cock_id")
    private Long cockId;
    @Column(name = "hen_id")
    private Long henId;
}
