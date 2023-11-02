package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.LoadData4InitProcessDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;

import java.util.List;

public interface ReproductionProcessService {
    List<ProcessForViewAllResponseDTO> findAllReproductionProcess();
    ReproductionProcessDTO addReproductionProcess(PairDTO pairDTO);
    void deleteReproductionProcess(Long id);
    ReproductionProcessDTO updateReproductionProcess(Long id, ReproductionProcessDTO reproductionProcessDto);

    LoadData4InitProcessDTOResponse getInitProcess();
//    BirdReproductionDto findFather(Long id);
    void setIsDoneForProcess(Long id);
    void separateBirdInProcess(Long processId, String cageId);
    void birdNotTolerateInProcess(Long processId);

}
