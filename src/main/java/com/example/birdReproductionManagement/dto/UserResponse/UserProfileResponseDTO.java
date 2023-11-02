package com.example.birdReproductionManagement.dto.UserResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private String userId;
    private String username;
    private String email;
//    private String password;
    private String fullName;
//    private String createdBy;
//    private Date createdDate;
    private String role;
    private String userImage;
}
