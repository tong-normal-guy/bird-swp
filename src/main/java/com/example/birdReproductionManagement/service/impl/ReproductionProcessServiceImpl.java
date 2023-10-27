package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4ProcessDTOResponse;
import com.example.birdReproductionManagement.dto.BirdTypeResponse.BirdType4ProcessInitDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import com.example.birdReproductionManagement.dto.PairDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.LoadData4InitProcessDTOResponse;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.ProcessForViewAllResponseDTO;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.BirdTypeNotMatchedException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.*;
import com.example.birdReproductionManagement.repository.*;
import com.example.birdReproductionManagement.service.BirdTypeService;
import com.example.birdReproductionManagement.service.CageService;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReproductionProcessServiceImpl implements ReproductionProcessService {
    private final ReproductionProcessRepository reproductionProcessRepository;
    private final BirdReproductionRepository birdReproductionRepository;
    private final BirdRepository birdRepository;
    private final CageRepository cageRepository;
    private final BirdTypeRepository birdTypeRepository;


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
            List<BirdReproduction> eggList = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.EGG);
            List<BirdReproduction> childList = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
            List<BirdReproduction> reproductionList = new ArrayList<>();
            reproductionList.addAll(eggList);
            reproductionList.addAll(childList);
            List<BirdReproductionDTO> reproductionListDTO = reproductionList.stream()
                    .map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList());
            process.setEggsList(reproductionListDTO);
            process.setBirdTypeName(cock.getBird().getBirdType().getName());
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
        ReproductionProcess finalReproductionProcess = reproductionProcess;
        ReflectionUtils.doWithFields(reproductionProcessDto.getClass(), field -> {
            field.setAccessible(true);
            Object newValue = field.get(reproductionProcessDto);
            if(newValue != null){
                String fieldName = field.getName();
                if(!fieldName.equals("cageId")){
                    Field existingField = ReflectionUtils.findField(finalReproductionProcess.getClass(), fieldName);
                    if(existingField != null){
                        ReflectionUtils.setField(existingField, finalReproductionProcess, newValue);
                    }
                }
            }
        });
        if(reproductionProcessDto.getCageId() != null){
            Cage newCage = cageRepository.findById(Long.valueOf(reproductionProcessDto.getCageId())).orElseThrow(()
                    -> new ReproductionProcessNotFoundException("Reproduction process could not be updated."));
            finalReproductionProcess.setCage(newCage);
        }
        if(finalReproductionProcess.getStage().equals("Nuôi con")){
            List<BirdReproduction> birdReproductions = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(finalReproductionProcess, ReproductionRole.CHILD);
            boolean flag = false;
            for (BirdReproduction birdReproductionWalker : birdReproductions){
                if (!birdReproductionWalker.getBird().getAgeRange().equals("Trưởng thành")){
                    flag = true;
                }
            }
            if (!flag){
                finalReproductionProcess.setIsDone(true);
            }
        }
        reproductionProcess = finalReproductionProcess;
        return ReproductionProcessMapper.mapToReproductionProcessDto(reproductionProcessRepository.save(reproductionProcess));
    }

    @Override
    public LoadData4InitProcessDTOResponse getInitProcess() {
//      cages
        List<CageDTO> cages = cageRepository.findCagesWithLocationStartingWithBAndQuantityZeroAndAvailableTrue()
                .stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
//      bird type
        List<BirdType4ProcessInitDTOResponse> birdType4ProcessInitDTOResponses = getType4ProcessInit();
//      build
        return LoadData4InitProcessDTOResponse.builder()
                .cage(cages)
                .birdType(birdType4ProcessInitDTOResponses)
                .build();
    }
    public List<BirdType4ProcessInitDTOResponse> getType4ProcessInit() {
        List<BirdType4ProcessInitDTOResponse> birdTypeDTOs = birdTypeRepository.findAll().stream().map(BirdTypeMapper::map2BirdType4ProcessInitDTO).collect(Collectors.toList());
        for (BirdType4ProcessInitDTOResponse birdTypeDTO:birdTypeDTOs) {
//      HENS
            List<Bird4ProcessDTOResponse> hens = birdRepository.findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdTypeSortedByMutationRateDesc(Sex.FEMALE, Long.parseLong(birdTypeDTO.getTypeId()))
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            birdTypeDTO.setHen(hens);
//      COCKS
            List<Bird4ProcessDTOResponse> cocks = birdRepository.findBirdsWhereIsDoneIsTrueAndSexIsAliveAndBirdTypeSortedByMutationRateDesc(Sex.MALE, Long.parseLong(birdTypeDTO.getTypeId()))
                    .stream().map(BirdMapper::map2Bird4ProcessDTO).collect(Collectors.toList());
            birdTypeDTO.setCock(cocks);
        }
        return birdTypeDTOs;
    }

//    @Override
//    public BirdReproductionDto findFather(Long id) {
//        return BirdReproductionMapper.mapToBirdReproductionDto(birdReproductionRepository
//                .findByReproductionProcessIdAndReproductionRoleEquals(id, ReproductionRole.FATHER));
//    }
}
