package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdTypeDto;
import com.example.birdReproductionManagement.service.BirdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/birdtypes")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT, RequestMethod.PATCH})
public class BirdTypeController {
    private BirdTypeService birdTypeService;
    @Autowired
    public BirdTypeController(BirdTypeService birdTypeService) {
        this.birdTypeService = birdTypeService;
    }
    @GetMapping("/view")
    public ResponseEntity<List<BirdTypeDto>> getListBirdTypes(){
        return new ResponseEntity<>(birdTypeService.findAllBirdTypes(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<BirdTypeDto> createBirdType(@RequestBody BirdTypeDto birdTypeDto){
        return new ResponseEntity<>(birdTypeService.createBirdType(birdTypeDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBirdType(@PathVariable("id") Long id){
        birdTypeService.deleteBirdType(id);
        return new ResponseEntity<>("Bird type is deleted.", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BirdTypeDto> updateBirdType(@PathVariable("id") Long id, @RequestBody BirdTypeDto birdTypeDto){
       return new ResponseEntity<>( birdTypeService.updateBirdType(id, birdTypeDto), HttpStatus.OK);
    }
}
