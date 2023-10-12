package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.Cage;
import com.example.birdReproductionManagement.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDivisionDto {
    private Long id;
    private Date createDate;
    private Date workDate;
    private Boolean isAttended;
    private String report;
    private UserEntityDto user;
    private CageDto cage;
}
