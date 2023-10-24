package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.EggDTO;
import com.example.birdReproductionManagement.dto.UpdateBirdReproductionDTO;
import com.example.birdReproductionManagement.service.BirdReproductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birdreproductions")
public class BirdReproductionController {
    private BirdReproductionService birdReproductionService;
    @Autowired
    public BirdReproductionController(BirdReproductionService birdReproductionService) {
        this.birdReproductionService = birdReproductionService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<BirdReproductionDTO>> getAllBirdReproductions(){
        return new ResponseEntity<>(birdReproductionService.findAllBirdReproductions(), HttpStatus.OK);
    }
    @PostMapping("/addegg/{id}")
    public ResponseEntity<List<BirdReproductionDTO>> addEggToProcess(@PathVariable("id")Long id, @RequestBody EggDTO eggDto){
        return new ResponseEntity<>(birdReproductionService.createBirdReproduction(id, eggDto), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BirdReproductionDTO> updateBirdReproduction(@PathVariable("id")Long id,
                                                                      @RequestBody UpdateBirdReproductionDTO updateBirdReproductionDTO){
        return new ResponseEntity<>(birdReproductionService.updateBirdReproduction(id, updateBirdReproductionDTO), HttpStatus.OK);
    }
    @GetMapping("/child/{id}")
    public ResponseEntity<List<BirdReproductionDTO>> getListChildOfProcess(@PathVariable("id")Long id){
        return new ResponseEntity<>(birdReproductionService.findChildOfProcess(id), HttpStatus.OK);
    }
}
