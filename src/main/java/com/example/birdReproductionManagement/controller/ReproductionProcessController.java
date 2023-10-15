package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.ReproductionProcessDto;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reproductionprocess")
@CrossOrigin(origins = "*")
public class ReproductionProcessController {
    private ReproductionProcessService reproductionProcessService;
    @Autowired
    public ReproductionProcessController(ReproductionProcessService reproductionProcessService) {
        this.reproductionProcessService = reproductionProcessService;
    }
    @GetMapping("/view")
    public ResponseEntity<List<ReproductionProcessDto>> getListOfReproductionProcess(){
        return new ResponseEntity<>(reproductionProcessService.findAllReproductionProcess(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ReproductionProcessDto> addReproductionProcess(@RequestBody ReproductionProcessDto reproductionProcessDto){
        return new ResponseEntity<>(reproductionProcessService.addReproductionProcess(reproductionProcessDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReproductionProcess(@PathVariable("id")Long id){
        reproductionProcessService.deleteReproductionProcess(id);
        return new ResponseEntity<>("Reproduction process is deleted.", HttpStatus.OK);
    }
    @PutMapping("/updated/{id}")
    public ResponseEntity<ReproductionProcessDto> updateReproductionProcess(@PathVariable("id")Long id, @RequestBody ReproductionProcessDto reproductionProcessDto){
        return new ResponseEntity<>(reproductionProcessService.updateReproductionProcess(id, reproductionProcessDto), HttpStatus.OK);
    }
}
