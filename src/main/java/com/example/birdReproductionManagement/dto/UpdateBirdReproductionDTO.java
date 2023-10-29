package com.example.birdReproductionManagement.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBirdReproductionDTO {
    //Data of egg
    private String eggStatus;
    private String eggType;
    private Date hatchDate;
    private Date swingBranchDate;
    private Date adultBirdDate;
    //Data of new bird
    private String sex;
    private String image;
    private Float weight;

}
