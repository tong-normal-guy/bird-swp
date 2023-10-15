package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.CageDto;
import com.example.birdReproductionManagement.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cages")
@CrossOrigin(origins = "*")
public class CageController {
    private CageService cageService;
    @Autowired
    public CageController(CageService cageService) {
        this.cageService = cageService;
    }
    @GetMapping("/view")
    public ResponseEntity<List<CageDto>> getListCages(){
        return new ResponseEntity<>(cageService.findAllCages(), HttpStatus.OK);
    }
    @PostMapping ("/create")
    public ResponseEntity<CageDto> addCage(@RequestBody CageDto cageDto){
        return new ResponseEntity<>(cageService.addCage(cageDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<CageDto> updateCage(@PathVariable("id")Long id, @RequestBody CageDto cageDto){
        return new ResponseEntity<>(cageService.updateCage(id, cageDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteCage(@PathVariable("id")Long id){
        cageService.deleteCage(id);
        return new ResponseEntity<>("Cage id deleted.", HttpStatus.OK);
    }
}
