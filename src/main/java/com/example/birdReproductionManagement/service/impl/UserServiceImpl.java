package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.UserDto;
import com.example.birdReproductionManagement.entity.User;
import com.example.birdReproductionManagement.exceptions.UserEmailExistedException;
import com.example.birdReproductionManagement.exceptions.UserNotFoundException;
import com.example.birdReproductionManagement.exceptions.UsernameExistedException;
import com.example.birdReproductionManagement.mapper.UserMapper;
import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.repository.UserRepository;
import com.example.birdReproductionManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public List<UserDto> findUserByRole(String role) {
        return userRepository.findAllByRole(Role.valueOf(role.toUpperCase())).stream()
                .map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()) != null){
            throw new UsernameExistedException("Username is existed.");
        }
        if(userRepository.findByEmail(userDto.getEmail()) != null){
            throw new UserEmailExistedException("User email is existed.");
        }
        User user = UserMapper.mapToUser(userDto);
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("User could not be updated."));
        if(!user.getEmail().equals(userDto.getEmail()) && userRepository.findByEmail(userDto.getEmail()) != null){
            throw new UserEmailExistedException("User email is existed.");
        }
        User newUser = UserMapper.mapToUser(userDto);
        newUser.setId(user.getId());
        return UserMapper.mapToUserDto(userRepository.save(newUser));
    }

    @Override
    public UserDto updateUserByFields(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User could not be found in updateUserByFields."));
        User finalUser = user;
        ReflectionUtils.doWithFields(userDto.getClass(), field -> {
            field.setAccessible(true);
            Object newValue = field.get(userDto);
            if(newValue != null){
                String fieldName = field.getName();
                if(!fieldName.equals("role")){
                    Field existingField = ReflectionUtils.findField(finalUser.getClass(), fieldName);
                    if(existingField != null){
                        existingField.setAccessible(true);
                        ReflectionUtils.setField(existingField, finalUser, newValue);
                    }
                }
            }
        });
        user = finalUser;
        if(userDto.getRole() != null){
            user.setRole(Role.valueOf(userDto.getRole()));
        }
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

}
