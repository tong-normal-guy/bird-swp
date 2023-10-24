package com.example.birdReproductionManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String createdBy;
    private Date createdDate;
    private String role;
    private String userImage;
//    private List<Role> roles = new ArrayList<>();
//    private List<WorkDivision> workDivisions = new ArrayList<>();
}
