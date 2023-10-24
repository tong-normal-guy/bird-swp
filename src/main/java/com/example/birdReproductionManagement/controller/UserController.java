package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.UserDTO;
import com.example.birdReproductionManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    @Autowired

    public UserController(UserService userEntityService) {
        this.userService = userEntityService;
    }
    @GetMapping("/view")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{role}")
    public ResponseEntity<List<UserDTO>> getListByRole(@PathVariable("role")String role){
        return new ResponseEntity<>(userService.findUserByRole(role), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createStaff(@RequestBody UserDTO userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateStaff(@PathVariable("id")Long id, @RequestBody UserDTO userDto){
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserByFields(@PathVariable("id")Long id, @RequestBody UserDTO userDto){
        return new ResponseEntity<>(userService.updateUserByFields(id, userDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Delete user success.", HttpStatus.OK);
    }
}
