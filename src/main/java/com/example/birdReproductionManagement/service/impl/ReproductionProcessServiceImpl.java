package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDto;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.BirdTypeNotMatchedException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.mapper.CageMapper;
import com.example.birdReproductionManagement.mapper.ReproductionProcessMapper;
import com.example.birdReproductionManagement.entity.Cage;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
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
    public List<ReproductionProcessDto> findAllReproductionProcess() {
        List<ReproductionProcessDto> list = reproductionProcessRepository.findAll().stream()
                .map(ReproductionProcessMapper::mapToReproductionProcessDto).collect(Collectors.toList());
        for (ReproductionProcessDto reproductionProcessDto : list){
            BirdReproductionDto cock = BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRoleEquals(Long.valueOf(reproductionProcessDto.getProcessId()), ReproductionRole.FATHER));
            reproductionProcessDto.setCockReproduction(cock);
            BirdReproductionDto hen = BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRoleEquals(Long.valueOf(reproductionProcessDto.getProcessId()), ReproductionRole.MOTHER));
            reproductionProcessDto.setHenReproduction(hen);
        }
        return list;
    }

    @Override
    public ReproductionProcessDto addReproductionProcess(PairDTO pairDTO) {
        Cage cage = cageRepository.findById(Long.valueOf(pairDTO.getCageId())).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Cage could not be found in addReproductionProcess."));

        BirdReproduction cock = new BirdReproduction();
        cock.setBird(birdRepository.findById(Long.valueOf(pairDTO.getCockId())).orElseThrow(()
                -> new BirdNotFoundException("Cock could not be found in addReproductionProcess.")));

        BirdReproduction hen = new BirdReproduction();
        hen.setBird(birdRepository.findById(Long.valueOf(pairDTO.getHenId())).orElseThrow(()
                -> new BirdNotFoundException("Hen could not be found in addReproductionProcess.")));

        if(!cock.getBird().getBirdType().getName().equals(hen.getBird().getBirdType().getName())){
            throw new BirdTypeNotMatchedException("Bird type of this birds pair is not matched.");
        }
        if(!cock.getBird().getAgeRange().equals("truong thanh") || !hen.getBird().getAgeRange().equals("truong thanh")){
            throw new BirdTypeNotMatchedException("Age range of cock is not suitable for reproduction");
        }
        //Create reproduction process and update quantity of cage
        ReproductionProcess reproductionProcess = new ReproductionProcess();
        reproductionProcess.setCage(cage);
        reproductionProcess.setPairingDate(new Date());
        cage.setQuantity(cage.getQuantity() + 2);
        cageRepository.save(cage);
        ReproductionProcess newProcess = reproductionProcessRepository.save(reproductionProcess);
        //Update new cage for cock and quantity of old cage
        Cage cockCage = cock.getBird().getCage();
        cockCage.setQuantity(cockCage.getQuantity() - 1);
        cageRepository.save(cockCage);
        cock.setReproductionRole(ReproductionRole.FATHER);
        cock.setReproductionProcess(reproductionProcess);
        cock.getBird().setCage(cage);
        birdReproductionRepository.save(cock);
        //Update new cage for hen and quantity of old cage
        Cage henCage = hen.getBird().getCage();
        henCage.setQuantity(henCage.getQuantity() - 1);
        cageRepository.save(henCage);
        hen.setReproductionRole(ReproductionRole.MOTHER);
        hen.setReproductionProcess(reproductionProcess);
        hen.getBird().setCage(cage);
        birdReproductionRepository.save(hen);

        return ReproductionProcessMapper.mapToReproductionProcessDto(newProcess);
    }

    @Override
    public void deleteReproductionProcess(Long id) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be deleted."));
        reproductionProcessRepository.delete(reproductionProcess);
    }

    @Override
    public ReproductionProcessDto updateReproductionProcess(Long id, ReproductionProcessDto reproductionProcessDto) {
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
