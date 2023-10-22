package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.UserDto;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
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

    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
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

    public static User4CageDetailDTOResponse map2User4CageDetailDTO(User userEntity){
        return User4CageDetailDTOResponse.builder()
                .userId(String.valueOf(userEntity.getId()))
                .fullName(userEntity.getFullName())
                .role(String.valueOf(userEntity.getRole()))
                .userImage(userEntity.getUserImage())
                .build();
    }
}
