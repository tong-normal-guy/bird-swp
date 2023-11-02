package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import com.example.birdReproductionManagement.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cages")
public class CageController {
    private CageService cageService;
    @Autowired
    public CageController(CageService cageService) {
        this.cageService = cageService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<CageDTO>> getListCages(){
        return new ResponseEntity<>(cageService.findAllCages(), HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<CageDetailDTOResponse> getCageDetailById(@PathVariable("id")Long id){
        return new ResponseEntity<>(cageService.getDetailById(id), HttpStatus.OK);
    }
    @GetMapping("/view")
    public ResponseEntity<List<CageDetailDTOResponse>> pickaCages(@RequestParam(name = "process",defaultValue = "false")
                                                                      Boolean process){
        return new ResponseEntity<List<CageDetailDTOResponse>>(cageService.pickaCages(process), HttpStatus.OK);
    }
    @GetMapping("/useable")
    public ResponseEntity<List<CageDTO>> viewCagesUseable(){
        return new ResponseEntity<List<CageDTO>>(cageService.viewCageUsable(), HttpStatus.OK);
    }
    @PostMapping ("/create")
    public ResponseEntity<CageDTO> addCage(@RequestBody CageDTO cageDto){
        return new ResponseEntity<>(cageService.addCage(cageDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CageDTO> updateCage(@PathVariable("id")Long id, @RequestBody CageDTO cageDto){
        return new ResponseEntity<>(cageService.updateCage(id, cageDto), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CageDTO> updateCageByFields(@PathVariable("id")Long id, @RequestBody CageDTO cageDto){
        return new ResponseEntity<>(cageService.updateCageByFields(id, cageDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCage(@PathVariable("id")Long id){
        cageService.deleteCage(id);
        return new ResponseEntity<>("Cage id deleted.", HttpStatus.OK);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<List<CageDTO>> findByLocation(@PathVariable("location")String location,
                                                        @RequestParam(name = "available", defaultValue = "false")
                                                        boolean available){
        return new ResponseEntity<>(cageService.findByLocation(location, available), HttpStatus.OK);
    }
    @GetMapping("/{location}")
    public ResponseEntity<List<CageDetailDTOResponse>> viewCageByLocation(@PathVariable("location")String location){
        return new ResponseEntity<>(cageService.viewCageByLocation(location), HttpStatus.OK);
    }

    @PostMapping("/addbird/{cageId}")
    public ResponseEntity<BirdDTO> addBirdToCage(@PathVariable("cageId")Long cageId, @RequestBody  BirdDTO birdDTO){
        return new ResponseEntity<>(cageService.addBirdToCage(cageId, birdDTO), HttpStatus.OK);
    }
}
