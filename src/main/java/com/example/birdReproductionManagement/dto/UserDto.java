package com.example.birdReproductionManagement.dto;

import com.example.birdReproductionManagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String createdBy;
    private Date createdDate;
    private Role role;
//    private List<Role> roles = new ArrayList<>();
//    private List<WorkDivision> workDivisions = new ArrayList<>();
}