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
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userEntityService;
    @Autowired

    public UserController(UserService userEntityService) {
        this.userEntityService = userEntityService;
    }
    @GetMapping("/staffs")
    public ResponseEntity<List<UserDto>> getListStaffs(){
        return new ResponseEntity<>(userEntityService.findAllStaffs(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createStaff(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userEntityService.addUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateStaff(@PathVariable("id")Long id, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userEntityService.updateUser(id, userDto), HttpStatus.OK);
    }
}
