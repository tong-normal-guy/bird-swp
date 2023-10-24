package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.EggDTO;
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
    public List<BirdReproductionDTO> findAllBirdReproductions() {
        List<BirdReproduction> birdReproductions = birdReproductionRepository.findAll();
        return birdReproductions.stream().map(BirdReproductionMapper::mapToBirdReproductionDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<BirdReproductionDTO> createBirdReproduction(Long processId, EggDTO eggDto) {
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
    public BirdReproductionDTO updateBirdReproduction(Long id, BirdReproductionDTO birdReproductionDto) {
        BirdReproduction birdReproduction = birdReproductionRepository.findById(id).orElseThrow(
                () -> new BirdReproductionNotFoundException("Bird reproduction could not be found."));
        Long processId = birdReproduction.getReproductionProcess().getId();
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
        }
        if(!birdReproductionDto.getEggStatus().equals("Hatched")
                && !birdReproductionDto.getEggStatus().equals("In development")){
            finalReproduction.setFail(true);
            finalReproduction.setFailDate(new Date());
        }
        birdReproduction = finalReproduction;
        birdReproductionRepository.save(birdReproduction);
        if(birdReproductionDto.getEggStatus().equals("Hatched")){
//      Tìm list các child của cock, đếm tổng số lượng (T) và số child có đột biến (M) -> mutationRate = M/T
            BirdReproduction cockReproduction = birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.FATHER);
            Bird cock = cockReproduction.getBird();
            List<BirdReproduction> cockReproductionList = birdReproductionRepository
                    .findByBirdAndReproductionRoleNot(cock, ReproductionRole.CHILD);
            List<ReproductionProcess> cockProcessList = new ArrayList<>();
//            int cockProcessNumber = 0;
            for(BirdReproduction cockWalker : cockReproductionList){
                cockProcessList.add(cockWalker.getReproductionProcess());
//                cockProcessNumber++;
            }
            List<BirdReproduction> superCockChildList = new ArrayList<>();
            for (ReproductionProcess reproductionProcess : cockProcessList){
                List<BirdReproduction>  cockChildList = birdReproductionRepository
                        .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
                superCockChildList.addAll(cockChildList);
            }
            int cockChildNumber = superCockChildList.size();
            int cockChildMutationNumber = 0;
            for (BirdReproduction cockWalker : superCockChildList){
                if(cockWalker.getBird().getMutation() != null){
                    cockChildMutationNumber++;
                }
            }
            if(cockChildNumber != 0){
                float cockMutationRate = (float) cockChildMutationNumber/cockChildNumber;
                cock.setMutationRate(cockMutationRate);
                birdRepository.save(cock);
            }

//      Tìm list các child của hen, đếm tổng số lượng (T) và số child có đột biến (M) -> mutationRate = M/T
            BirdReproduction henReproduction = birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.MOTHER);
            Bird hen = henReproduction.getBird();
            List<BirdReproduction> henReproductionList = birdReproductionRepository
                    .findByBirdAndReproductionRoleNot(hen, ReproductionRole.CHILD);
            List<ReproductionProcess> henProcessList = new ArrayList<>();
//            int henProcessNumber = 0;
            for (BirdReproduction henWalker : henReproductionList){
                henProcessList.add(henWalker.getReproductionProcess());
//                henProcessNumber++;
            }
            List<BirdReproduction> superHenChildList = new ArrayList<>();
            for (ReproductionProcess reproductionProcess : henProcessList){
                List<BirdReproduction>  henChildList = birdReproductionRepository
                        .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
                superHenChildList.addAll(henChildList);
            }
            int henChildNumber = superHenChildList.size();
            int henChildMutationNumber = 0;
            for (BirdReproduction henWalker : superHenChildList){
                if(henWalker.getBird().getMutation() != null){
                    henChildMutationNumber++;
                }
            }
            if(henChildNumber != 0){
                float henMutationRate = (float) henChildMutationNumber/henChildNumber;
                hen.setMutationRate(henMutationRate);
                birdRepository.save(hen);
            }
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
}
