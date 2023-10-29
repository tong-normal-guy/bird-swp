package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.DashboardDTOResponse;
import com.example.birdReproductionManagement.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    @GetMapping
    public ResponseEntity<DashboardDTOResponse> getDashboard(){
        return new ResponseEntity<>(dashboardService.getDashboard(), HttpStatus.OK);
    }
}
