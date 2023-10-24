package com.example.birdReproductionManagement.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

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
    private Date actSwingBranchDate;
    private Date actAdultBirdDate;
    //Data of new bird
    private String sex;
    private String image;
    private Float weight;

}
