package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessInitDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.LoadData4InitProcessDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessDetailResponseDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;
import com.example.birdReproductionManagement.service.BirdTypeService;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reproductionprocess")
public class ReproductionProcessController {
    @Autowired
    private ReproductionProcessService reproductionProcessService;
    @Autowired
    private BirdTypeService birdTypeService;

    @GetMapping("/view")
    public ResponseEntity<List<ProcessForViewAllResponseDTO>> getListOfReproductionProcess(){
        return new ResponseEntity<>(reproductionProcessService.findAllReproductionProcess(), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProcessForViewAllResponseDTO>> getListOfReproductionProcesses(){
        return new ResponseEntity<>(reproductionProcessService.findAllReproductionProcesses(), HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<ProcessDetailResponseDTO> getProcessDetailById(@PathVariable("id")long id){
        return new ResponseEntity<>(reproductionProcessService.getProcessDetailById(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ReproductionProcessDTO> addReproductionProcess(@RequestBody PairDTO pairDTO,
                                                                         @RequestParam(name = "confirm", defaultValue = "false")
                                                                         boolean confirm){
        return new ResponseEntity<>(reproductionProcessService.addReproductionProcess(pairDTO, confirm), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReproductionProcess(@PathVariable("id")Long id){
        reproductionProcessService.deleteReproductionProcess(id);
        return new ResponseEntity<>("Reproduction process is deleted.", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ReproductionProcessDTO> updateReproductionProcess(@PathVariable("id")Long id,
                                                                            @RequestBody ReproductionProcessDTO reproductionProcessDto){
        return new ResponseEntity<>(reproductionProcessService.updateReproductionProcess(id, reproductionProcessDto), HttpStatus.OK);
    }
//    @GetMapping("/init")
//    public ResponseEntity<List<BirdType4ProcessInitDTOResponse>> getType4ProcessInit(){
//        return new  ResponseEntity<List<BirdType4ProcessInitDTOResponse>>(birdTypeService.getType4ProcessInit(), HttpStatus.OK);
//    }

    @GetMapping("/init")
    public ResponseEntity<LoadData4InitProcessDTOResponse> getInitProcess(){
        return new  ResponseEntity<LoadData4InitProcessDTOResponse>(reproductionProcessService.getInitProcess(), HttpStatus.OK);
    }
    @PatchMapping("/done/{id}")
    public ResponseEntity<String> setIsDoneForProcess(@PathVariable("id")Long id, @RequestParam(name = "emotion", required = false) String emotion){
//        if (emotion != null)
        if (reproductionProcessService.setIdDoneWhenEmotion(id, emotion)){
            return new ResponseEntity<>("Reproduction process with id " + id + "is end.", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Reproduction process with id " + id + "is fail as eggs exit in process.", HttpStatus.BAD_REQUEST);
//        else reproductionProcessService.setIsDoneForProcess(id);
    }

    @PostMapping("/separate/{cageId}")
    public ResponseEntity<String> separateBirdInProcess(@PathVariable("cageId")Long cageId,
                                                        @RequestBody CageDTO cageDTO){
        reproductionProcessService.separateBirdInProcess(cageId, cageDTO.getCageId());
        return new ResponseEntity<>("Separate success.", HttpStatus.OK);
    }

    @GetMapping("/hate")
    public ResponseEntity<List<PairDTO>> getListBirdEmotionHATE(){
        return new ResponseEntity<>(reproductionProcessService.getListBirdEmotionHATE(), HttpStatus.OK);
    }

//    @GetMapping("/findfather/{id}")
//    public ResponseEntity<BirdReproductionDto> findFather(@PathVariable("id")Long id){
//        return new ResponseEntity<>(reproductionProcessService.findFather(id), HttpStatus.OK);
//    }
}
