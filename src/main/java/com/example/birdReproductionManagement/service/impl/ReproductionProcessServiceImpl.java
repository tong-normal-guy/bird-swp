package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.BirdTypeNotMatchedException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.mapper.ReproductionProcessMapper;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReproductionProcessServiceImpl implements ReproductionProcessService {
    private ReproductionProcessRepository reproductionProcessRepository;
    private BirdReproductionRepository birdReproductionRepository;
    private BirdRepository birdRepository;
    private CageRepository cageRepository;
    @Autowired
    public ReproductionProcessServiceImpl(ReproductionProcessRepository reproductionProcessRepository, BirdReproductionRepository birdReproductionRepository, BirdRepository birdRepository, CageRepository cageRepository) {
        this.reproductionProcessRepository = reproductionProcessRepository;
        this.birdReproductionRepository = birdReproductionRepository;
        this.birdRepository = birdRepository;
        this.cageRepository = cageRepository;
    }

    @Override
    public List<ProcessForViewAllResponseDTO> findAllReproductionProcess() {
        List<ProcessForViewAllResponseDTO> list = reproductionProcessRepository.findAll().stream()
                .map(ReproductionProcessMapper::mapToProcessForViewAllResponseDTO).collect(Collectors.toList());
        for (ProcessForViewAllResponseDTO process : list){
            ReproductionProcess reproductionProcess = reproductionProcessRepository
                    .findById(Long.valueOf(process.getProcessId())).orElseThrow(
                            () -> new ReproductionProcessNotFoundException("Process could not be found in findAllReproductionProcess."));
            BirdReproductionDTO cock = BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRole(Long.valueOf(process.getProcessId()), ReproductionRole.FATHER));
            process.setCockId(cock.getBird().getBirdId());
            BirdReproductionDTO hen = BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRole(Long.valueOf(process.getProcessId()), ReproductionRole.MOTHER));
            process.setHenId(hen.getBird().getBirdId());
            List<BirdReproduction> childList = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.EGG);
            List<BirdReproductionDTO> childListDTO = childList.stream()
                    .map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList());
            process.setEggsList(childListDTO);
        }
        return list;
    }

    @Override

    public ReproductionProcessDTO addReproductionProcess(PairDTO pairDTO) {
        Cage cage = cageRepository.findById(Long.valueOf(pairDTO.getCageId())).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Cage could not be found in addReproductionProcess."));
        if(cage.getQuantity() > 0){
            throw new BirdTypeNotMatchedException("This cage had another reproduction process going on.");
        }
        Bird cock = birdRepository.findById(Long.valueOf(pairDTO.getCockId())).orElseThrow(()
                -> new BirdNotFoundException("Cock could not be found in addReproductionProcess."));
        Bird hen = birdRepository.findById(Long.valueOf(pairDTO.getHenId())).orElseThrow(()
                -> new BirdNotFoundException("Hen could not be found in addReproductionProcess."));
        //Kiểm tra loại chim có giống nhau không
        if(!cock.getBirdType().getName().equals(hen.getBirdType().getName())){
            throw new BirdTypeNotMatchedException("Bird type of this birds pair is not matched.");
        }
        //Kiểm tra lứa tuổi của chim có phù hợp không
        if(!cock.getAgeRange().equals("Trưởng thành") || !hen.getAgeRange().equals("Trưởng thành")){
            throw new BirdTypeNotMatchedException("Age range of cock is not suitable for reproduction");
        }
        //Kiểm tra chim có đang trong quá trình sinh sản khác không
        if(birdReproductionRepository
                .existsByBirdAndReproductionRoleNotAndReproductionRoleNotAndReproductionProcessIsDone(cock,
                        ReproductionRole.CHILD, ReproductionRole.EGG, false) || birdReproductionRepository
                .existsByBirdAndReproductionRoleNotAndReproductionRoleNotAndReproductionProcessIsDone(hen,
                        ReproductionRole.CHILD, ReproductionRole.EGG, false)){
            throw new BirdTypeNotMatchedException("Cock or hen bird is engaged in another reproduction process.");
        }
        //Create bird reproduction for cock and hen
        BirdReproduction cockReproduction = new BirdReproduction();
        cockReproduction.setBird(cock);
        BirdReproduction henReproduction = new BirdReproduction();
        henReproduction.setBird(hen);
        //Create reproduction process and update quantity of cage
        ReproductionProcess reproductionProcess = new ReproductionProcess();
        reproductionProcess.setIsDone(false);
        reproductionProcess.setCage(cage);
        reproductionProcess.setPairingDate(new Date());
        reproductionProcess.setTotalEgg(0);
        ReproductionProcess newProcess = reproductionProcessRepository.save(reproductionProcess);
        int number = cage.getQuantity() + 2;
        cage.setQuantity(number);
        cageRepository.save(cage);
        //Update new cage for cock and quantity of old cage
        Cage cockCage = cock.getCage();
        number = cockCage.getQuantity() - 1;
        cockCage.setQuantity(number);
        cageRepository.save(cockCage);
        cockReproduction.setReproductionRole(ReproductionRole.FATHER);
        cockReproduction.setReproductionProcess(reproductionProcess);
        cock.setCage(cage);
        birdReproductionRepository.save(cockReproduction);
        //Update new cage for hen and quantity of old cage
        Cage henCage = hen.getCage();
        number = henCage.getQuantity() - 1;
        henCage.setQuantity(number);
        cageRepository.save(henCage);
        henReproduction.setReproductionRole(ReproductionRole.MOTHER);
        henReproduction.setReproductionProcess(reproductionProcess);
        hen.setCage(cage);
        birdReproductionRepository.save(henReproduction);

        return ReproductionProcessMapper.mapToReproductionProcessDto(newProcess);
    }

    @Override
    public void deleteReproductionProcess(Long id) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be deleted."));
        reproductionProcessRepository.delete(reproductionProcess);
    }

    @Override
    public ReproductionProcessDTO updateReproductionProcess(Long id, ReproductionProcessDTO reproductionProcessDto) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be updated."));
        Cage cage = cageRepository.findById(Long.valueOf(reproductionProcessDto.getCageId())).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be updated."));
        reproductionProcessDto.setCage(CageMapper.mapToCageDto(cage));
        return ReproductionProcessMapper.mapToReproductionProcessDto(reproductionProcessRepository.save(ReproductionProcessMapper.mapToReproductionProcess(reproductionProcessDto)));
    }

//    @Override
//    public BirdReproductionDto findFather(Long id) {
//        return BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
//                .findByReproductionProcessIdAndReproductionRoleEquals(id, ReproductionRole.FATHER));
//    }
}
