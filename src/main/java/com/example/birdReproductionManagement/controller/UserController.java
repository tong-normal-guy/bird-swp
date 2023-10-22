package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.UserDto;
import com.example.birdReproductionManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;
    @Autowired

    public UserController(UserService userEntityService) {
        this.userService = userEntityService;
    }
    @GetMapping("/view")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{role}")
    public ResponseEntity<List<UserDto>> getListByRole(@PathVariable("role")String role){
        return new ResponseEntity<>(userService.findUserByRole(role), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createStaff(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateStaff(@PathVariable("id")Long id, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserByFields(@PathVariable("id")Long id, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUserByFields(id, userDto), HttpStatus.OK);
    }
}
