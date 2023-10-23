package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDto;
import com.example.birdReproductionManagement.dto.EggDto;
import com.example.birdReproductionManagement.entity.Bird;
import com.example.birdReproductionManagement.entity.BirdReproduction;
import com.example.birdReproductionManagement.entity.ReproductionProcess;
import com.example.birdReproductionManagement.entity.ReproductionRole;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.BirdReproductionNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
import com.example.birdReproductionManagement.service.BirdReproductionService;
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
    @Override
    public List<BirdReproductionDto> findAllBirdReproductions() {
        List<BirdReproduction> birdReproductions = birdReproductionRepository.findAll();
        return birdReproductions.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<BirdReproductionDto> createBirdReproduction(Long processId, EggDto eggDto) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(processId).orElseThrow(()
                -> new ReproductionProcessNotFoundException("Egg could not be added."));
        List<BirdReproduction> newEggs = new ArrayList<>();
        for (int i = 0; i < eggDto.getNumber(); i++){
            BirdReproduction newEgg = new BirdReproduction();
            newEgg.setEggLaidDate(eggDto.getLaidDate());
            newEgg.setReproductionProcess(reproductionProcess);
            newEgg.setEggStatus("In development");
            newEgg.setReproductionRole(ReproductionRole.EGG);
            newEggs.add(birdReproductionRepository.save(newEgg));
        }
        reproductionProcess.setTotalEgg(reproductionProcess.getTotalEgg() + eggDto.getNumber());
        reproductionProcessRepository.save(reproductionProcess);
        return newEggs.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }

    @Override
    public BirdReproductionDto updateBirdReproduction(Long id, BirdReproductionDto birdReproductionDto) {
        BirdReproduction birdReproduction = birdReproductionRepository.findById(id).orElseThrow(
                () -> new BirdReproductionNotFoundException("Bird reproduction could not be found."));
        BirdReproduction finalReproduction = birdReproduction;
        ReflectionUtils.doWithFields(birdReproductionDto.getClass(), field -> {
            field.setAccessible(true);
            Object newValue = field.get(birdReproductionDto);
            if(newValue != null){
                String fieldName = field.getName();
                if(!fieldName.equals("birdId")){
                    Field existingField = ReflectionUtils.findField(finalReproduction.getClass(), fieldName);
                    if(existingField != null){
                        existingField.setAccessible(true);
                        ReflectionUtils.setField(existingField, finalReproduction, newValue);
                    }
                }
            }
        });
        if(birdReproductionDto.getBirdId() != null){
            Bird bird = birdRepository.findById(Long.valueOf(birdReproductionDto.getBirdId())).orElseThrow(
                    () -> new BirdNotFoundException("Bird could not be found in updateBirdReproduction"));
            finalReproduction.setBird(bird);
        }
        if(birdReproductionDto.getEggStatus().equals("Hatched")){
            finalReproduction.setReproductionRole(ReproductionRole.CHILD);

            BirdReproduction cockReproduction = birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRoleEquals(id, ReproductionRole.FATHER);
            Bird cock = cockReproduction.getBird();
            List<BirdReproduction> cockReproductionList = birdReproductionRepository
                    .findByBirdAndReproductionRoleNot(cock, ReproductionRole.CHILD);
            List<ReproductionProcess> cockProcessList = new ArrayList<>();
            int cockProcessNumber = 0;
            for(BirdReproduction cockWalker : cockReproductionList){
                cockProcessList.add(cockWalker.getReproductionProcess());
                cockProcessNumber++;
            }


            BirdReproduction henReproduction = birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRoleEquals(id, ReproductionRole.MOTHER);
            Bird hen = henReproduction.getBird();
            List<BirdReproduction> henReproductionList = birdReproductionRepository
                    .findByBirdAndReproductionRoleNot(hen, ReproductionRole.CHILD);
            List<ReproductionProcess> henProcessList = new ArrayList<>();
            int henProcessNumber = 0;
            for (BirdReproduction henWalker : henReproductionList){
                henProcessList.add(henWalker.getReproductionProcess());
                henProcessNumber++;
            }

        }
        if(!birdReproductionDto.getEggStatus().equals("Hatched")
                && !birdReproductionDto.getEggStatus().equals("In development")){
            finalReproduction.setFail(true);
            finalReproduction.setFailDate(new Date());
        }
        birdReproduction = finalReproduction;
        return BirdReproductionMapper.mapToBirdReproductionDto(birdReproduction);
    }
}
