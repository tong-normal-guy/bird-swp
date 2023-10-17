package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.UserDto;
import com.example.birdReproductionManagement.mapper.UserMapper;
import com.example.birdReproductionManagement.entity.Role;
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
    public List<UserDto> findAllStaffs() {
        return userRepository.findAllByRole(Role.valueOf("STAFF")).stream()
                .map(UserMapper::mapToUserEntityDto).collect(Collectors.toList());
    }

//    @Override
//    public UserEntityDto createUser(UserEntityDto userEntityDto){
//        return UserEntityMapper.mapToUserEntityDto(userRepository.save(UserEntityMapper.mapToUserEntity(userEntityDto)));
//    }
}
