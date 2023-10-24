package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();
    List<UserDTO> findUserByRole(String role);
    UserDTO addUser(UserDTO userDto);
    UserDTO updateUser(Long id, UserDTO userDto);
    UserDTO updateUserByFields(Long id, UserDTO userDto);
    void deleteUser(Long id);
}
