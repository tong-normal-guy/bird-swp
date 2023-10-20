package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.CageResponse.Cage4ListDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDto;
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
    @GetMapping("/all")
    public ResponseEntity<List<CageDto>> getListCages(){
        return new ResponseEntity<>(cageService.findAllCages(), HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<CageDto> getCageDetailById(@PathVariable("id")Long id){
        return new ResponseEntity<>(cageService.getDetailById(id), HttpStatus.OK);
    }
    @GetMapping("/view")
    public ResponseEntity<List<CageDetailDTOResponse>> pickaCages(@RequestParam(name = "process",defaultValue = "false") Boolean process){
        return new ResponseEntity<>(cageService.pickaCages(process), HttpStatus.OK);
    }
    @PostMapping ("/add")
    public ResponseEntity<CageDto> addCage(@RequestBody CageDto cageDto){
        return new ResponseEntity<>(cageService.addCage(cageDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CageDto> updateCage(@PathVariable("id")Long id, @RequestBody CageDto cageDto){
        return new ResponseEntity<>(cageService.updateCage(id, cageDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCage(@PathVariable("id")Long id){
        cageService.deleteCage(id);
        return new ResponseEntity<>("Cage id deleted.", HttpStatus.OK);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<List<CageDto>> findByLocation(@PathVariable("location")String location){
        return new ResponseEntity<>(cageService.findByLocation(location), HttpStatus.OK);
    }


}
