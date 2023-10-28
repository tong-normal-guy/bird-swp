package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDetailReponseDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdForListResponseDTO;
import com.example.birdReproductionManagement.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/birds")
public class BirdController {
    private final BirdService birdService;
    @Autowired
    public BirdController(BirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<BirdForListResponseDTO>> getListBirds(){
        return new ResponseEntity<>(birdService.findAllBirds(), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BirdDetailReponseDTO> getBirdDetailById(@PathVariable("id")Long id){
        return new ResponseEntity<>(birdService.getBirdDetailById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirdDTO> updateBird(@PathVariable("id")Long id, @RequestBody BirdDTO birdDto){
        return new ResponseEntity<>(birdService.updateBird(id, birdDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBird(@PathVariable("id") Long id){
        birdService.deleteBird(id);
        return  new ResponseEntity<>("Bird is deleted", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBird(@RequestBody BirdDTO birdDto){
        return new ResponseEntity<>(birdService.createBird(birdDto), HttpStatus.CREATED);
    }

//    @GetMapping("/view/{id}")
//    public ResponseEntity<List<BirdDTO>> getListBirdsByCage(@PathVariable("id")Long id){
//        return new ResponseEntity<>(birdService.findByCage(id), HttpStatus.OK);
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<BirdDTO> updateBirdByFields(@PathVariable("id")Long id, @RequestBody BirdDTO birdDto){
        return new ResponseEntity<>(birdService.updateBirdByFields(id, birdDto), HttpStatus.OK);
    }

    @GetMapping("/sex/{sex}")
    public ResponseEntity<List<BirdDTO>> findBirdBySex(@PathVariable("sex")String sex){
        return new ResponseEntity<>(birdService.findBySex(sex), HttpStatus.OK);
    }
    @GetMapping("/outcast")
    public ResponseEntity<List<BirdDTO>> findOutcastBirds(){
        return new ResponseEntity<>(birdService.findOutcastBirds(), HttpStatus.OK);
    }
}
