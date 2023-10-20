package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
import com.example.birdReproductionManagement.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/birds")
@CrossOrigin(origins = "*")
public class BirdController {
    private final BirdService birdService;
    @Autowired
    public BirdController(BirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<BirdDto>> getListBirds(){
        List<BirdDto> birdDtos = birdService.findAllBirds();
        return new ResponseEntity<>(birdDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirdDto> updateBird(@PathVariable("id")Long id, @RequestBody BirdDto birdDto){
        return new ResponseEntity<>(birdService.updateBird(id, birdDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBird(@PathVariable("id") Long id){
        birdService.deleteBird(id);
        return  new ResponseEntity<>("Bird is deleted", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBird(@RequestBody BirdDto birdDto){
        return new ResponseEntity<>(birdService.createBird(birdDto), HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<List<BirdDto>> getListBirdsByCage(@PathVariable("id")Long id){
        return new ResponseEntity<>(birdService.findByCage(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BirdDto> updateBirdByFields(@PathVariable("id")Long id, @RequestBody BirdDto birdDto){
        return new ResponseEntity<>(birdService.updateBirdByFields(id, birdDto), HttpStatus.OK);
    }
}
