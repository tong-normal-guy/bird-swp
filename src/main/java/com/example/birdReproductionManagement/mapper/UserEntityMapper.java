package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.UserDto;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.User;

public class UserEntityMapper {
    public static User mapToUserEntity(UserDto userEntityDto){
        return User.builder()
                .id(userEntityDto.getId())
                .username(userEntityDto.getUsername())
                .email(userEntityDto.getEmail())
                .password(userEntityDto.getPassword())
                .fullName(userEntityDto.getFullName())
                .createdBy(userEntityDto.getCreatedBy())
                .createdDate(userEntityDto.getCreatedDate())
                .role(userEntityDto.getRole())
                .build();
    }

    public static UserDto mapToUserEntityDto(User userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .fullName(userEntity.getFullName())
                .createdBy(userEntity.getCreatedBy())
                .createdDate(userEntity.getCreatedDate())
                .role(userEntity.getRole())
                .build();
    }

    public static User4CageDetailDTOResponse map2User4CageDetailDTO(User userEntity){
        return User4CageDetailDTOResponse.builder()
                .userId(userEntity.getId())
                .fullName(userEntity.getFullName())
                .role(String.valueOf(userEntity.getRole()))
                .build();
    }
}
