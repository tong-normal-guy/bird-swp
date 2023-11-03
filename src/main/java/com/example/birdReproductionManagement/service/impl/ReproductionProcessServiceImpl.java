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
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.*;
import com.example.birdReproductionManagement.repository.*;
import com.example.birdReproductionManagement.service.ReproductionProcessService;
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
public class ReproductionProcessServiceImpl implements ReproductionProcessService {
    private final ReproductionProcessRepository reproductionProcessRepository;
    private final BirdReproductionRepository birdReproductionRepository;
    private final BirdRepository birdRepository;
    private final CageRepository cageRepository;
    private final BirdTypeRepository birdTypeRepository;
    private final BirdEmotionRepository emotionRepository;

    @Override
    public List<ProcessForViewAllResponseDTO> findAllReproductionProcess() {
        List<ProcessForViewAllResponseDTO> list = reproductionProcessRepository.findAll().stream()
                .map(ReproductionProcessMapper::mapToProcessForViewAllResponseDTO).collect(Collectors.toList());
        for (ProcessForViewAllResponseDTO process : list){
            ReproductionProcess reproductionProcess = reproductionProcessRepository
                    .findById(Long.valueOf(process.getProcessId())).orElseThrow(
                            () -> new ReproductionProcessNotFoundException("Process could not be found in findAllReproductionProcess."));
            //Tìm thông tin chim cha
            BirdReproduction cock = birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRole(Long.valueOf(process.getProcessId()), ReproductionRole.FATHER);
                BirdReproductionDTO cockDTO = BirdReproductionMapper.mapToBirdReproductionDto(cock);
                process.setCockId(cockDTO.getBird().getBirdId());
            //Tìm thông tin chim mẹ
            BirdReproduction hen = birdReproductionRepository
                    .findByReproductionProcessIdAndReproductionRole(Long.valueOf(process.getProcessId()), ReproductionRole.MOTHER);
                BirdReproductionDTO henDTO = BirdReproductionMapper.mapToBirdReproductionDto(hen);
                process.setHenId(henDTO.getBird().getBirdId());
            //Tìm danh sách trứng
            List<BirdReproduction> eggList = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.EGG);
            //Tìm danh sách chim con
            List<BirdReproduction> childList = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
            //Cho vào danh sách tổng
            List<BirdReproduction> reproductionList = new ArrayList<>();
            reproductionList.addAll(eggList);
            reproductionList.addAll(childList);
            List<BirdReproductionDTO> reproductionListDTO = reproductionList.stream()
                    .map(BirdReproductionMapper::mapToBirdReproductionDto).collect(Collectors.toList());
            process.setEggsList(reproductionListDTO);
            process.setBirdTypeName(cock.getBird().getBirdType().getName());
            //Kiểm tra chim non sau khi trưởng thành đã được chuyển lồng hay chưa
            Cage processCage = reproductionProcess.getCage();
            for (BirdReproductionDTO birdReproductionDTO : reproductionListDTO){
                if(birdReproductionDTO.getReproductionRole().equals("CHILD")){
                    if(birdReproductionDTO.getBird().getCage() != null){
                        String childCageId = birdReproductionDTO.getBird().getCage().getCageId();
                        if (!childCageId.equals(String.valueOf(processCage.getId()))){
                            birdReproductionDTO.setIsMoved(true);
                        }
                    }
                }
            }
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
        reproductionProcess.setFailEgg(0);
        ReproductionProcess newProcess = reproductionProcessRepository.save(reproductionProcess);
        int number = cage.getQuantity() + 2;
        cage.setQuantity(number);
        cageRepository.save(cage);
        //Update new cage for cock and quantity of old cage
        Cage cockCage = cock.getCage();
        if (cockCage != null){
            number = cockCage.getQuantity() - 1;
            cockCage.setQuantity(number);
            cageRepository.save(cockCage);
        }
        cockReproduction.setReproductionRole(ReproductionRole.FATHER);
        cockReproduction.setReproductionProcess(reproductionProcess);
        cock.setCage(cage);
        birdReproductionRepository.save(cockReproduction);
        //Update new cage for hen and quantity of old cage
        Cage henCage = hen.getCage();
        if(henCage != null){
            number = henCage.getQuantity() - 1;
            henCage.setQuantity(number);
            cageRepository.save(henCage);
        }
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
        if(reproductionProcess.getCage() != null){
            Cage cage = reproductionProcess.getCage();
            cage.setQuantity(0);
            cageRepository.save(cage);
        }
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
                        existingField.setAccessible(true);
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
//        if(finalReproductionProcess.getIsDone() != null && finalReproductionProcess.getIsDone()){
//            List<BirdReproduction> birdReproductions = birdReproductionRepository
//                    .findAllByReproductionProcess_Id(finalReproductionProcess.getId());
//            for (BirdReproduction birdReproduction : birdReproductions){
//                if(birdReproduction.getBird() != null){
//                    Bird bird = birdReproduction.getBird();
//                    bird.setCage(null);
//                    birdRepository.save(bird);
//                }
//            }
//            Cage processCage = finalReproductionProcess.getCage();
//            processCage.setQuantity(0);
//            cageRepository.save(processCage);
//        }
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

    @Override
    public void setIsDoneForProcess(Long id) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id)
                .orElseThrow(() -> new ReproductionProcessNotFoundException("Reproduction process could not be found."));
        //Kiểm tra quá trình có đủ điều kiện để kết thúc chưa

        //Set lồng null cho tất chim có trong quá trình
        List<BirdReproduction> birdReproductions = birdReproductionRepository
                .findAllByReproductionProcess_Id(reproductionProcess.getId());
        for (BirdReproduction birdReproduction : birdReproductions){
            if(birdReproduction.getBird() != null){
                Bird bird = birdReproduction.getBird();
                bird.setCage(null);
                birdRepository.save(bird);
            }
        }
        Cage processCage = reproductionProcess.getCage();
        processCage.setQuantity(0);
        cageRepository.save(processCage);
        reproductionProcess.setIsDone(true);
        reproductionProcessRepository.save(reproductionProcess);

    }

    @Override
    public Boolean setIdDoneWhenEmotion(Long id, String emotion) {
        ReproductionProcess process = reproductionProcessRepository.findById(id)
                .orElseThrow(() -> new ReproductionProcessNotFoundException("process not found"));
        List<BirdReproduction> eggs = birdReproductionRepository.findAllByReproductionProcessIdAndReproductionRoleAndIsFailFalse(id, ReproductionRole.EGG);
        if (!eggs.isEmpty()){
            return false;
        }
        if (emotion != null){
            BirdReproduction cockRe = birdReproductionRepository.findByReproductionProcessIdAndReproductionRole(id, ReproductionRole.FATHER);
            BirdReproduction henRe = birdReproductionRepository.findByReproductionProcessIdAndReproductionRole(id, ReproductionRole.MOTHER);
            Bird cock = cockRe.getBird();
            Bird hen = henRe.getBird();
            BirdEmotion emotionn = new BirdEmotion();
            emotionn.setEmotion(Emotion.valueOf(emotion.toUpperCase()));
            emotionn.setCock(cock);
            emotionn.setHen(hen);
            BirdEmotionId birdEmotionId = new BirdEmotionId(cock.getId(), hen.getId());
            emotionn.setId(birdEmotionId);
            emotionRepository.save(emotionn);
        }
        List<BirdReproduction> birdReproductions = birdReproductionRepository
                .findAllByReproductionProcess_Id(process.getId());
        for (BirdReproduction birdReproduction : birdReproductions){
            if(birdReproduction.getBird() != null){
                Bird bird = birdReproduction.getBird();
                bird.setCage(null);
                birdRepository.save(bird);
            }
        }
        Cage cage = process.getCage();
        cage.setQuantity(0);
        cageRepository.save(cage);
        process.setIsDone(true);
        reproductionProcessRepository.save(process);
        return true;
    }

    @Override
    public void separateBirdInProcess(Long cageId, String birdCageId) {
        Cage cage = cageRepository.findById(cageId).orElseThrow(
                () -> new CageNotFoundException("Cage could not be found in separateBirdInProcess."));
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage(cage);
        Long processId = reproductionProcess.getId();
        Cage newCage = cageRepository.findById(Long.valueOf(birdCageId)).orElseThrow(
                () -> new CageNotFoundException("Cage could not be found in separateBirdInProcess."));
        Cage processCage = reproductionProcess.getCage();
        BirdReproduction birdReproduction = birdReproductionRepository
                .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.FATHER);
        Bird bird = birdReproduction.getBird();
        bird.setCage(newCage);
        birdRepository.save(bird);
        processCage.setQuantity(processCage.getQuantity() - 1);
        newCage.setQuantity(newCage.getQuantity() + 1);
        cageRepository.save(processCage);
        cageRepository.save(newCage);
        reproductionProcess.setSeparateDate(new Date());
        reproductionProcessRepository.save(reproductionProcess);
        calculateRateProcess(reproductionProcess);
    }

    @Override
    public void birdNotTolerateInProcess(Long processId) {
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(processId).orElseThrow(
                () -> new ReproductionProcessNotFoundException("Process could not be found in birdNotTolerateInProcess."));
        reproductionProcess.setIsDone(true);
        BirdReproduction cockReproduction = birdReproductionRepository
                .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.FATHER);
        Bird cock = cockReproduction.getBird();
        cock.setCage(null);
        birdRepository.save(cock);
        BirdReproduction henReproduction = birdReproductionRepository
                .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.MOTHER);
        Bird hen = henReproduction.getBird();
        hen.setCage(null);
        birdRepository.save(hen);
        reproductionProcessRepository.save(reproductionProcess);
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

    private void calculateRateProcess(ReproductionProcess reproductionProcess){
        long processId = reproductionProcess.getId();
        //Tìm chim trống tham gia vào process đã cho
        BirdReproduction cockReproduction = birdReproductionRepository
                .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.FATHER);
        Bird cock = cockReproduction.getBird();
        //Tính tỉ lệ đột biến của chim trống
        calculateMutationRate(cock);
        //Tính tỉ lệ sinh sản của chim trống
        calculateReproductionRate(cock);
        //Tìm chim mái tham gia vào process đã cho
        BirdReproduction henReproduction = birdReproductionRepository
                .findByReproductionProcessIdAndReproductionRole(processId, ReproductionRole.MOTHER);
        Bird hen = henReproduction.getBird();
        //Tính tỉ lệ đột biến của chim mái
        calculateMutationRate(hen);
        //Tính tỉ lệ sinh sản của chim mái
        calculateReproductionRate(hen);
    }

    private void calculateReproductionRate(Bird bird){
        List<ReproductionProcess> reproductionProcesses = reproductionProcessRepository
                .findParentReproductionProcessByBird(bird);
        int eggNumber = 0;
        int childNumber = 0;
        if (reproductionProcesses != null){
            for (ReproductionProcess process : reproductionProcesses){
                childNumber = childNumber + birdReproductionRepository.countByReproductionProcessAndReproductionRoleCHILD(process);
                eggNumber = eggNumber + process.getTotalEgg();
            }
            if (eggNumber != 0){
                float reproductionRate = childNumber / eggNumber;
                bird.setSuperReproduct(reproductionRate);
                birdRepository.save(bird);
            }
        }
    }

    private void calculateMutationRate(Bird bird){
        int childNumber = 0;
        int mutationChildNumber = 0;
        List<ReproductionProcess> reproductionProcesses = reproductionProcessRepository
                .findParentReproductionProcessByBird(bird);
        if (reproductionProcesses != null){
            for (ReproductionProcess process : reproductionProcesses){
                //Đếm số chim con và số chim con đột biến trong mỗi process, sau đó cộng thêm vào tổng
                childNumber = childNumber + birdRepository
                        .countBirdByReproductionProcessAndReproductionRole(process, ReproductionRole.CHILD);
                mutationChildNumber = mutationChildNumber + birdRepository
                        .countBirdByMutationAndReproductionProcessAndReproductionRole(process, ReproductionRole.CHILD);
            }
            if(childNumber != 0) {
                float mutationRate = mutationChildNumber / childNumber;
                bird.setMutationRate(mutationRate);
                birdRepository.save(bird);
            }
        }
    }
}
