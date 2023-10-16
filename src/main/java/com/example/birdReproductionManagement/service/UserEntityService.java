package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.UserEntityDto;

import java.util.List;

public interface UserEntityService {
    List<UserEntityDto> findAllStaffs();
    UserEntityDto createUser(UserEntityDto userEntityDto);
}
