package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.model.Cage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CageTypeDto {
    private Long id;
    private String name;
    private String size;
    private String description;
//    private List<CageDto> cageList;
}
