package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDto;
import com.example.birdReproductionManagement.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}/update")
    public ResponseEntity<BirdDto> updateBird(@RequestBody BirdDto birdDto, @PathVariable("id") Long id){
        BirdDto birdDto1 = birdService.updateBird(birdDto, id);
        return new ResponseEntity<>(birdDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteBird(@PathVariable("id") Long id){
        birdService.deleteBird(id);
        return  new ResponseEntity<>("Bird is deleted", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBird(@RequestBody BirdDto birdDto){
        return new ResponseEntity<>(birdService.createBird(birdDto), HttpStatus.CREATED);
    }

    @GetMapping("/view/{cageid}")
    public ResponseEntity<List<BirdDto>> getListBirdsByCage(@PathVariable("cageid")Long cageId){
        return new ResponseEntity<>(birdService.findByCage(cageId), HttpStatus.OK);
    }
}
