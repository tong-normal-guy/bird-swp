package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.entity.Bird;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BirdParentDTO {
    Bird birdParent;
    int size = 0;
}
