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
@RequestMapping("/api/birdreproductions")
//@CrossOrigin(origins = "*")
public class BirdReproductionController {
    private BirdReproductionService birdReproductionService;
    @Autowired
    public BirdReproductionController(BirdReproductionService birdReproductionService) {
        this.birdReproductionService = birdReproductionService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<BirdReproductionDto>> getAllBirdReproductions(){
        return new ResponseEntity<>(birdReproductionService.findAllBirdReproductions(), HttpStatus.OK);
    }
    @PostMapping("/addegg/{id}")
    public ResponseEntity<List<BirdReproductionDto>> addEggToProcess(@PathVariable("id")Long id, @RequestBody EggDto eggDto){
        return new ResponseEntity<>(birdReproductionService.createBirdReproduction(id, eggDto), HttpStatus.CREATED);
    }
}
