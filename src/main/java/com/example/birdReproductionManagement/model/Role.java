package com.example.birdReproductionManagement.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public enum Role{
    ADMIN,
    STAFF,
    MANAGER
}
