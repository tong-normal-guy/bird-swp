package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.service.BirdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birdtypes")
public class BirdTypeController {
    @Autowired
    private BirdTypeService birdTypeService;
    @Autowired
    public BirdTypeController(BirdTypeService birdTypeService) {
        this.birdTypeService = birdTypeService;
    }
    @GetMapping("/view")
    public ResponseEntity<List<BirdTypeDTO>> getListBirdTypes(){
        return new ResponseEntity<>(birdTypeService.findAllBirdTypes(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<BirdTypeDTO> createBirdType(@RequestBody BirdTypeDTO birdTypeDto){
        return new ResponseEntity<>(birdTypeService.createBirdType(birdTypeDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBirdType(@PathVariable("id") Long id){
        birdTypeService.deleteBirdType(id);
        return new ResponseEntity<>("Bird type is deleted.", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BirdTypeDTO> updateBirdType(@PathVariable("id") Long id, @RequestBody BirdTypeDTO birdTypeDto){
       return new ResponseEntity<>( birdTypeService.updateBirdType(id, birdTypeDto), HttpStatus.OK);
    }
}
