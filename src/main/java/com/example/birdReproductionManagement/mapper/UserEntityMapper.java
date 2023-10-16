package com.example.birdReproductionManagement.mapper;

import com.example.birdReproductionManagement.dto.UserEntityDto;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.model.UserEntity;

public class UserEntityMapper {
    public static UserEntity mapToUserEntity(UserEntityDto userEntityDto){
        return UserEntity.builder()
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

    public static UserEntityDto mapToUserEntityDto(UserEntity userEntity){
        return UserEntityDto.builder()
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

    public static User4CageDetailDTOResponse map2User4CageDetailDTO(UserEntity userEntity){
        return User4CageDetailDTOResponse.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .role(String.valueOf(userEntity.getRole()))
                .build();
    }
}
