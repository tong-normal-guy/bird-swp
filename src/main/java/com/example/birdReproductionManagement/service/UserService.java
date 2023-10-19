package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllStaffs();
    UserDto addUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
}
