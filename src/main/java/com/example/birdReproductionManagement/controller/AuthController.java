package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.LoginDTO;
import com.example.birdReproductionManagement.dto.UserDTO;
import com.example.birdReproductionManagement.dto.UserResponse.UserProfileResponseDTO;
import com.example.birdReproductionManagement.entity.User;
import com.example.birdReproductionManagement.mapper.UserMapper;
import com.example.birdReproductionManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<UserProfileResponseDTO> login(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(loginDto.getUsername());
        UserProfileResponseDTO userDTO = UserMapper.mapToUserProfileResponseDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
//        if(userRepository.existsByUsername(registerDto.getUsername())){
//            return new ResponseEntity<>("Username is existed.", HttpStatus.BAD_REQUEST);
//        }
//        User user = new User();
//        user.setUsername(registerDto.getUsername());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        user.setRole(Role.STAFF);
//        userRepository.save(user);
//        return new ResponseEntity<>("Account is created successfully.", HttpStatus.OK);
//    }
}
