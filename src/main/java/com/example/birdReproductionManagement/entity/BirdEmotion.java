package com.example.birdReproductionManagement.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bird_emotion")
public class BirdEmotion {
    @EmbeddedId
    private BirdEmotionId id;
    private Emotion emotion;
//  relation
    @ManyToOne
    @JoinColumn(name = "cock_id", insertable = false, updatable = false)
    private Bird cock;
    @ManyToOne
    @JoinColumn(name = "hen_id", insertable = false, updatable = false)
    private Bird hen;
}
