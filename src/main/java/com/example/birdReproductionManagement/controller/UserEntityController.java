package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.UserEntityDto;
import com.example.birdReproductionManagement.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserEntityController {
    private UserEntityService userEntityService;
    @Autowired

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }
    @GetMapping("/staffs")
    public ResponseEntity<List<UserEntityDto>> getListStaffs(){
        return new ResponseEntity<>(userEntityService.findAllStaffs(), HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createStaff(@RequestBody UserEntityDto userEntityDto){
//        return new ResponseEntity<>(userEntityService.createUser(userEntityDto), HttpStatus.CREATED);
//    }
}
