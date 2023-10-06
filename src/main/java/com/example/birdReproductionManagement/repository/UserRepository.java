package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
