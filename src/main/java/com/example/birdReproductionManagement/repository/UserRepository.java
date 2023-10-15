package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    List<UserEntity> findByRole(Role role);
    @Query("SELECT a FROM UserEntity a WHERE a.role = :role")
    List<UserEntity> findAllByRole(Role role);
}
