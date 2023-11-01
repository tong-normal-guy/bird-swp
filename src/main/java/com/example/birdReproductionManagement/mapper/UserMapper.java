package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.UserDTO;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.UserResponse.UserProfileResponseDTO;
import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.entity.User;

public class UserMapper {
    public static User mapToUser(UserDTO userDto){
        return User.builder()
//                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .fullName(userDto.getFullName())
                .createdBy(userDto.getCreatedBy())
                .createdDate(userDto.getCreatedDate())
                .role(Role.valueOf(userDto.getRole()))
                .userImage(userDto.getUserImage())
                .build();
    }

    public static UserDTO mapToUserDto(User user){
        return UserDTO.builder()
                .userId(String.valueOf(user.getId()))
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .createdBy(user.getCreatedBy())
                .createdDate(user.getCreatedDate())
                .role(String.valueOf(user.getRole()))
                .userImage(user.getUserImage())
                .build();
    }

    public static User4CageDetailDTOResponse map2User4CageDetailDTO(User user) {
//        if (user != null)
            return User4CageDetailDTOResponse.builder()
                    .userId(String.valueOf(user.getId()))
                    .fullName(user.getFullName())
                    .role(String.valueOf(user.getRole()))
                    .userImage(user.getUserImage())
                    .build();
//        else throw new RuntimeException("error at user is null");
    }

    public static UserProfileResponseDTO mapToUserProfileResponseDTO(User user){
        return UserProfileResponseDTO.builder()
                .userId(String.valueOf(user.getId()))
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .userImage(user.getUserImage())
                .role(user.getRole().name())
                .build();
    }
}
