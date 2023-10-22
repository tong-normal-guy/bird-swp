package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    List<UserDto> findUserByRole(String role);
    UserDto addUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto updateUserByFields(Long id, UserDto userDto);
    void deleteUser(Long id);
}
