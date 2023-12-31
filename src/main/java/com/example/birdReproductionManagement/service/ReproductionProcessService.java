package com.example.birdReproductionManagement.service;

import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.LoadData4InitProcessDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessDetailResponseDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;

import java.util.List;

public interface ReproductionProcessService {
    List<ProcessForViewAllResponseDTO> findAllReproductionProcess();
    List<ProcessForViewAllResponseDTO> findAllReproductionProcesses();
    ProcessDetailResponseDTO getProcessDetailById(Long processId);
    ReproductionProcessDTO addReproductionProcess(PairDTO pairDTO, boolean confirm);
    void deleteReproductionProcess(Long id);
    ReproductionProcessDTO updateReproductionProcess(Long id, ReproductionProcessDTO reproductionProcessDto);

    LoadData4InitProcessDTOResponse getInitProcess();
//    BirdReproductionDto findFather(Long id);
    void setIsDoneForProcess(Long id);
    void separateBirdInProcess(Long cageId, String birdCageId);
    void birdNotTolerateInProcess(Long processId);
    Boolean setIdDoneWhenEmotion(Long id, String emotion);

    List<PairDTO> getListBirdEmotionHATE();
}
