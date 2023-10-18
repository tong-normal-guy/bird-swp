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
                    .findByReproductionProcessIdAndReproductionRoleEquals(reproductionProcessDto.getId(), ReproductionRole.FATHER));
            reproductionProcessDto.setCockReproduction(cock);
            BirdReproductionDto hen = BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRoleEquals(reproductionProcessDto.getId(), ReproductionRole.MOTHER));
            reproductionProcessDto.setHenReproduction(hen);
        }
        return list;
    }




    @Override
    public ReproductionProcessDto addReproductionProcess(PairDTO pairDTO) {
        Cage cage = cageRepository.findById(pairDTO.getCageId()).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Reproduction process could not be created."));
        ReproductionProcess reproductionProcess = new ReproductionProcess();
        reproductionProcess.setCage(cage);
        reproductionProcess.setPairingDate(new Date());
        cage.setQuantity(cage.getQuantity() + 2);
        cageRepository.save(cage);
        ReproductionProcess newProcess = reproductionProcessRepository.save(reproductionProcess);

        BirdReproduction cock = new BirdReproduction();
        cock.setBird(birdRepository.findById(pairDTO.getCockId()).orElseThrow(()
                -> new BirdNotFoundException("Reproduction process could not be created.")));

        BirdReproduction hen = new BirdReproduction();
        hen.setBird(birdRepository.findById(pairDTO.getHenId()).orElseThrow(()
                -> new BirdNotFoundException("Reproduction process could not be created.")));

        if(!cock.getBird().getBirdType().getName().equals(hen.getBird().getBirdType().getName())){
            reproductionProcessRepository.delete(reproductionProcess);
            cage.setQuantity(cage.getQuantity() + 2);
            cageRepository.save(cage);
            throw new BirdTypeNotMatchedException("Bird type of this birds pair is not matched.");
        }
        cock.setReproductionRole(ReproductionRole.FATHER);
        cock.setReproductionProcess(reproductionProcess);
        cock.getBird().setCage(cage);
        birdReproductionRepository.save(cock);

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
        Cage cage = cageRepository.findById(reproductionProcessDto.getCageId()).orElseThrow(()
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
