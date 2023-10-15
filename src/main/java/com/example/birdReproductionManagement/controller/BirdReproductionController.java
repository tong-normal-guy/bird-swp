package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.EggDto;
import com.example.birdReproductionManagement.service.BirdReproductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birdReproduction")
@CrossOrigin(origins = "*")
public class BirdReproductionController {
    private BirdReproductionService birdReproductionService;
    @Autowired
    public BirdReproductionController(BirdReproductionService birdReproductionService) {
        this.birdReproductionService = birdReproductionService;
    }
    @PostMapping("/eggs/add")
    public ResponseEntity<List<BirdReproductionDto>> addEgg(@RequestBody EggDto eggDto){
        return new ResponseEntity<>(birdReproductionService.addEggs(eggDto), HttpStatus.CREATED);
    }
    @PutMapping("/eggs/update")
    public ResponseEntity<BirdReproductionDto> updateEgg(@RequestBody BirdReproductionDto birdReproductionDto){
        return new ResponseEntity<>(birdReproductionService.updateBirdReproduction(birdReproductionDto), HttpStatus.OK);
    }
}
