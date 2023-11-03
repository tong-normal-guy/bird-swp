package com.example.birdReproductionManagement.service.impl;
import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.EggDTO;
import com.example.birdReproductionManagement.dto.UpdateBirdReproductionDTO;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.BirdReproductionNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.repository.*;
import com.example.birdReproductionManagement.service.BirdReproductionService;
import com.example.birdReproductionManagement.utils.MyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BirdReproductionServiceImpl implements BirdReproductionService {
    private final BirdReproductionRepository birdReproductionRepository;
    private final ReproductionProcessRepository reproductionProcessRepository;
    private final BirdRepository birdRepository;
    private final BirdTypeRepository birdTypeRepository;
    private final CageRepository cageRepository;
    @Override
    public List<BirdReproductionDTO> findAllBirdReproductions() {
        List<BirdReproduction> birdReproductions = birdReproductionRepository.findAll();
        return birdReproductions.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<BirdReproductionDTO> createBirdReproduction(Long cageId, EggDTO eggDto) {
        Cage cage = cageRepository.findById(cageId).orElseThrow(
                () -> new CageNotFoundException("Cage could not be found."));
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage(cage);
        Long processId = reproductionProcess.getId();
        BirdReproduction birdReproduction = birdReproductionRepository
                .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.FATHER);
        Bird bird = birdReproduction.getBird();
        BirdType birdType = bird.getBirdType();
        List<BirdReproduction> newEggs = new ArrayList<>();
        for (int i = 0; i < eggDto.getNumber(); i++){
            BirdReproduction newEgg = new BirdReproduction();
            newEgg.setEggLaidDate(eggDto.getLaidDate());
            newEgg.setReproductionProcess(reproductionProcess);
            newEgg.setEggStatus("Đang phát triển");
            newEgg.setReproductionRole(ReproductionRole.EGG);
            Date expEggHatchDate = MyUtils.calculateDate(newEgg.getEggLaidDate(), birdType.getIncubate());
            newEgg.setExpEggHatchDate(expEggHatchDate);
            Date expSwingBranchDate = MyUtils.calculateDate(newEgg.getExpEggHatchDate(), birdType.getChick());
            newEgg.setExpSwingBranchDate(expSwingBranchDate);
            Date expAdultBirdDate = MyUtils.calculateDate(newEgg.getExpSwingBranchDate(), birdType.getSwingBranch());
            newEgg.setExpAdultBirdDate(expAdultBirdDate);
            newEggs.add(birdReproductionRepository.save(newEgg));
        }
        reproductionProcess.setTotalEgg(reproductionProcess.getTotalEgg() + eggDto.getNumber());
        reproductionProcessRepository.save(reproductionProcess);
        return newEggs.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }

    @Override
    public BirdReproductionDTO updateBirdReproduction(Long id, UpdateBirdReproductionDTO updateBirdReproductionDTO) {
        BirdReproduction birdReproduction = birdReproductionRepository.findById(id).orElseThrow(
                () -> new BirdReproductionNotFoundException("Bird reproduction could not be found."));
        Cage cage = birdReproduction.getReproductionProcess().getCage();
        Long processId = birdReproduction.getReproductionProcess().getId();
        ReproductionProcess process = reproductionProcessRepository.findById(processId).orElseThrow(
                () -> new ReproductionProcessNotFoundException("Process could not be found."));
        BirdReproduction finalReproduction = birdReproduction;
        ReflectionUtils.doWithFields(updateBirdReproductionDTO.getClass(), field -> {
            field.setAccessible(true);
            Object newValue = field.get(updateBirdReproductionDTO);
            if(newValue != null){
                String fieldName = field.getName();
                if(!fieldName.equals("sex") && !fieldName.equals("image") && !fieldName.equals("weight")
                        && !fieldName.equals("hatchDate")){
                    Field existingField = ReflectionUtils.findField(finalReproduction.getClass(), fieldName);
                    if(existingField != null){
                        existingField.setAccessible(true);
                        ReflectionUtils.setField(existingField, finalReproduction, newValue);
                    }
                }
            }
        });
        if(updateBirdReproductionDTO.getEggStatus() != null){
            if(updateBirdReproductionDTO.getEggStatus().equals("Đã nở")){
                finalReproduction.setReproductionRole(ReproductionRole.CHILD);
                finalReproduction.setFail(false);
                finalReproduction.setFailDate(null);
                Bird newChick = BirdMapper.mapToBird(updateBirdReproductionDTO);
                newChick.setAgeRange("Non");
                newChick.setIsAlive(true);
                BirdType chickType = birdReproductionRepository
                        .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.FATHER)
                        .getBird().getBirdType();
                newChick.setBirdType(chickType);
                finalReproduction.setExpSwingBranchDate(MyUtils
                        .calculateDate(newChick.getHatchDate(), newChick.getBirdType().getChick()));
                finalReproduction.setExpAdultBirdDate(MyUtils
                        .calculateDate(finalReproduction.getExpSwingBranchDate(), newChick.getBirdType().getSwingBranch()));
                newChick.setCage(cage);
                Bird bird = birdRepository.save(newChick);
                finalReproduction.setBird(bird);
//                finalReproduction.setActEggHatchDate(updateBirdReproductionDTO.getHatchDate());
            }
            if(!updateBirdReproductionDTO.getEggStatus().equals("Đã nở")
                    && !updateBirdReproductionDTO.getEggStatus().equals("Đang phát triển")){
                if(finalReproduction.getBird() != null){
                    Bird deletedBird = finalReproduction.getBird();
                    finalReproduction.setReproductionRole(ReproductionRole.valueOf("EGG"));
                    finalReproduction.setBird(null);
                    birdReproductionRepository.save(finalReproduction);
                    birdRepository.delete(deletedBird);
                }
                finalReproduction.setFail(true);
                finalReproduction.setFailDate(new Date());
                reproductionProcessRepository.save(process);
            }
            birdReproduction = finalReproduction;
            birdReproductionRepository.save(birdReproduction);
            cage.setQuantity(birdRepository.countByCageAndReproductionProcess(process,cage));
            cageRepository.save(cage);
            process.setFailEgg(birdReproductionRepository
                    .countByReproductionProcessAndReproductionRoleEGGAndEggStatus(process, "Hỏng"));
            process.setTotalEgg(birdReproductionRepository
                    .countByReproductionProcessAndReproductionRoleEGG(process));
            reproductionProcessRepository.save(process);
        }
        return BirdReproductionMapper.mapToBirdReproductionDto(birdReproduction);
    }
    @Override
    public List<BirdReproductionDTO> findChildOfProcess(Long id) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id)
                .orElseThrow(() -> new ReproductionProcessNotFoundException("Process could not be found."));
        List<BirdReproduction> childList = birdReproductionRepository
                .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
        return childList.stream().map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList());
    }

    @Override
    public void deleteBirdReproduction(Long id) {
        BirdReproduction birdReproduction = birdReproductionRepository.findById(id).orElseThrow(
                () -> new BirdReproductionNotFoundException("Bird reproduction could not be found."));
        ReproductionProcess reproductionProcess = birdReproduction.getReproductionProcess();
        if(reproductionProcess.getCage() != null){
            if(!birdReproduction.getReproductionRole().name().equals("EGG")){
                Cage cage = reproductionProcess.getCage();
                if(cage.getQuantity() != 0){
                    cage.setQuantity(cage.getQuantity() - 1);
                    cageRepository.save(cage);
                }
            }
        }
        birdReproductionRepository.delete(birdReproduction);
    }
}
