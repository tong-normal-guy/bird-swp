package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.UserEntityDto;
import com.example.birdReproductionManagement.mapper.UserEntityMapper;
import com.example.birdReproductionManagement.model.Role;
import com.example.birdReproductionManagement.model.UserEntity;
import com.example.birdReproductionManagement.repository.UserRepository;
import com.example.birdReproductionManagement.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private UserRepository userRepository;
    @Autowired
    public UserEntityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<UserEntityDto> findAllStaffs() {
        return userRepository.findAllByRole(Role.valueOf("STAFF")).stream()
                .map(UserEntityMapper::mapToUserEntityDto).collect(Collectors.toList());
    }
}
