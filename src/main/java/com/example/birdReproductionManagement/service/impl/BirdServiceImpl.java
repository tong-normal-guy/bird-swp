package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.*;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.repository.*;
import com.example.birdReproductionManagement.service.BirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BirdServiceImpl implements BirdService {
    private final BirdRepository birdRepository;
    private final BirdTypeRepository birdTypeRepository;
    private final CageRepository cageRepository;
    private final BirdReproductionRepository birdReproductionRepository;
    private final ReproductionProcessRepository reproductionProcessRepository;

    @Override
    public List<BirdForListResponseDTO> findAllBirds() {
        List<Bird> birds = birdRepository.findAll();
        List<BirdForListResponseDTO> birdDTOs = birds.stream().map(BirdMapper::mapToBirdForListResponseDTO)
                .collect(Collectors.toList());
//        for (BirdDetailReponseDTO birdWalker : birdDTOs){
//            //Tìm chim bố và chim mẹ
//            int gen = 0;
//            findPedigree(birdWalker, gen);
////            BirdReproduction birdReproduction = birdReproductionRepository.findByBirdAndReproductionRole(bird, ReproductionRole.CHILD);
////            if(birdReproduction != null){
////                ReproductionProcess reproductionProcess = birdReproduction.getReproductionProcess();
////                Bird father = birdReproductionRepository
////                        .findByReproductionProcessIdAndReproductionRole(reproductionProcess.getId(), ReproductionRole.FATHER)
////                        .getBird();
////                BirdDetailReponseDTO fatherDTO = BirdMapper.mapToBirdDetailReponseDTO(father);
////                birdWalker.setFather(fatherDTO);
////                Bird mother = birdReproductionRepository
////                        .findByReproductionProcessIdAndReproductionRole(reproductionProcess.getId(), ReproductionRole.MOTHER)
////                        .getBird();
////                BirdDetailReponseDTO motherDTO = BirdMapper.mapToBirdDetailReponseDTO(mother);
////                birdWalker.setMother(motherDTO);
////            }
//            //Tìm danh sách các chim con
//            Bird bird = birdRepository.findById(Long.valueOf(birdWalker.getBirdId())).orElseThrow(
//                    () -> new BirdNotFoundException("Bird could not be found."));
//            List<BirdReproduction> birdReproductions = birdReproductionRepository
//                    .findByBirdAndReproductionRoleNot(bird, ReproductionRole.CHILD);
//            List<ReproductionProcess> reproductionProcesses = new ArrayList<>();
//            for (BirdReproduction birdReproductionWalker : birdReproductions){
//                reproductionProcesses.add(birdReproductionWalker.getReproductionProcess());
//            }
//            List<Bird> descendantsList = new ArrayList<>();
//            List<BirdReproduction> descendantsReproductions;
//            for (ReproductionProcess reproductionProcess : reproductionProcesses){
//                descendantsReproductions = birdReproductionRepository
//                        .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
//                for (BirdReproduction descendantReproduction : descendantsReproductions){
//                    descendantsList.add(descendantReproduction.getBird());
//                }
//            }
//            List<DescendantResponseDTO> descendantsDTOs = descendantsList.stream().map(BirdMapper::mapToDescendantResponseDTO)
//                    .collect(Collectors.toList());
//            birdWalker.setDescendants(descendantsDTOs);
//        }
        return birdDTOs;
    }

    @Override
    public BirdDetailReponseDTO getBirdDetailById(Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(
                () -> new BirdNotFoundException("Bird could not be found."));
        BirdDetailReponseDTO birdDetailReponseDTO = BirdMapper.mapToBirdDetailReponseDTO(bird);
        BirdForPedigreeResponseDTO birdForPedigreeResponseDTO = BirdMapper.mapToBirdForPedigreeResponseDTO(bird);
        int gen = 0;
        findPedigree(birdForPedigreeResponseDTO, gen);
        birdDetailReponseDTO.setFather(birdForPedigreeResponseDTO.getFather());
        birdDetailReponseDTO.setMother(birdForPedigreeResponseDTO.getMother());

        List<BirdReproduction> birdReproductions = birdReproductionRepository
                .findByBirdAndReproductionRoleNot(bird, ReproductionRole.CHILD);
        List<ReproductionProcess> reproductionProcesses = new ArrayList<>();
        for (BirdReproduction birdReproductionWalker : birdReproductions){
            reproductionProcesses.add(birdReproductionWalker.getReproductionProcess());
        }
        List<Bird> descendantsList = new ArrayList<>();
        List<BirdReproduction> descendantsReproductions;
        for (ReproductionProcess reproductionProcess : reproductionProcesses){
            descendantsReproductions = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
            for (BirdReproduction descendantReproduction : descendantsReproductions){
                descendantsList.add(descendantReproduction.getBird());
            }
        }
        List<DescendantResponseDTO> descendantsDTOs = descendantsList.stream().map(BirdMapper::mapToDescendantResponseDTO)
                .collect(Collectors.toList());
        birdDetailReponseDTO.setDescendants(descendantsDTOs);
        return birdDetailReponseDTO;
    }

    @Override
    public BirdDTO updateBird(Long id, BirdDTO birdDto) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be updated."));

        Bird updatedBird = BirdMapper.mapToBird(birdDto);
        updatedBird.setId(bird.getId());
        updatedBird.setBirdType(bird.getBirdType());
        Cage cage;
        if (!Long.valueOf(birdDto.getCageId()).equals(bird.getCage().getId())){
            cage =  cageRepository.findById(Long.valueOf(birdDto.getCageId())).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
            Cage oldCage = cageRepository.findById(bird.getCage().getId()).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
            oldCage.setQuantity(oldCage.getQuantity() - 1);
            cage.setQuantity(cage.getQuantity() + 1);
            cageRepository.save(oldCage);
            cageRepository.save(cage);
        }else {
            cage = cageRepository.findById(bird.getCage().getId()).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be updated."));
        }
        updatedBird.setCage(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(updatedBird));
    }

    @Override
    public void deleteBird(Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be deleted."));
        if(bird.getCage() != null){
            Cage cage = bird.getCage();
            cage.setQuantity(cage.getQuantity() - 1);
            cageRepository.save(cage);
        }

        birdRepository.delete(bird);
    }

    @Override
    public BirdDTO createBird(BirdDTO birdDto) {
        Bird bird = BirdMapper.mapToBird(birdDto);
        BirdType birdType = birdTypeRepository.findByName(birdDto.getBirdTypeName());
        bird.setBirdType(birdType);
        if(birdDto.getCageId() != null){
            Cage cage = cageRepository.findById(Long.valueOf(birdDto.getCageId())).orElseThrow(()
                    -> new CageNotFoundException("Bird could not be created."));
            cage.setQuantity(cage.getQuantity() + 1);
            cageRepository.save(cage);
            bird.setCage(cage);
        }
        return BirdMapper.mapToBirdDto(birdRepository.save(bird));
    }

    @Override
    public List<BirdDTO> findByCage(Long id) {
        return birdRepository.findByCage_Id(id).stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }

    @Override
    public BirdDTO updateBirdByFields(Long id, BirdDTO birdDto) {
        Bird bird = birdRepository.findById(id).orElseThrow(()
                -> new BirdNotFoundException("Bird could not be updated."));
        Bird finalBird = bird;
        ReflectionUtils.doWithFields(birdDto.getClass(), field -> {
            field.setAccessible(true); // Đảm bảo rằng chúng ta có thể truy cập các trường private
            Object newValue = field.get(birdDto);
            if (newValue != null) { // lấy các giá trị ko null
                String fieldName = field.getName();
                if (!fieldName.equals("cageId") && !fieldName.equals("sex") && !fieldName.equals("birdTypeName")) {
                    Field existingField = ReflectionUtils.findField(finalBird.getClass(), fieldName);
                    if (existingField != null) {
                        existingField.setAccessible(true);
                        ReflectionUtils.setField(existingField, finalBird, newValue);
                    }
                }
            }
        });
        //Lưu ngày cập nhật lứa tuổi của chim
        if(birdDto.getAgeRange() != null){
            if (!bird.getAgeRange().equals(birdDto.getAgeRange())){
                saveActDate(birdDto.getAgeRange(), finalBird);
            }
            if(birdReproductionRepository.existsByBirdAndReproductionProcessIsDone(bird, false)){
                if(birdDto.getAgeRange().equals("Trưởng thành")){
                    BirdReproduction birdReproduction = birdReproductionRepository.findByBirdAndReproductionRole(bird, ReproductionRole.CHILD);
                    ReproductionProcess reproductionProcess = birdReproduction.getReproductionProcess();
                    boolean eggExisted = birdReproductionRepository
                            .existsByReproductionRoleAndReproductionProcessAndEggStatusEquals(ReproductionRole.EGG,
                                    reproductionProcess, "In development");
                    if (!eggExisted){
                        List<BirdReproduction> birdReproductions = birdReproductionRepository
                                .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
                        boolean flag = false;
                        for (BirdReproduction birdReproductionWalker : birdReproductions){
                            if (!birdReproductionWalker.getBird().getAgeRange().equals("Trưởng thành")){
                                flag = true;
                            }
                        }
                        if (!flag){
                            reproductionProcess.setIsDone(true);
                            reproductionProcessRepository.save(reproductionProcess);
                        }
                    }
                }
            }
        }
//        Cage cage = null;
        //Kiểm tra chim non đã trưởng thành hết chưa để kết thúc process
        if(birdDto.getCageId() != null){
            Cage cage = cageRepository.findById(Long.valueOf(birdDto.getCageId())).orElseThrow(
                    () -> new CageNotFoundException("Cage could not be found in updateBirdByFields with" + birdDto.getCageId()));
            if(bird.getCage() != null){
                Cage oldCage = cageRepository.findById(bird.getCage().getId()).orElseThrow(
                        () -> new CageNotFoundException("Cage could not be found in updateBirdByFields with" + birdDto.getCageId()));
                oldCage.setQuantity(oldCage.getQuantity() - 1);
                cageRepository.save(oldCage);
            }
            cage.setQuantity(cage.getQuantity() + 1);
            cageRepository.save(cage);
            finalBird.setCage(cage);
        }
        if(birdDto.getBirdTypeName() != null){
            BirdType birdType = birdTypeRepository.findByName(birdDto.getBirdTypeName());
            finalBird.setBirdType(birdType);
        }
        if(birdDto.getSex() != null){
            finalBird.setSex(Sex.valueOf(birdDto.getSex()));
        }
//        else{
//            cage = cageRepository.findById(bird.getCage().getId()).orElseThrow(
//                    () -> new CageNotFoundException("Cage could not be found in updateBirdByFields with" + birdDto.getCageId()));
//        }
        bird = finalBird;
//        bird.setCage(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(bird));
    }

    @Override
    public List<BirdDTO> findBySex(String sex) {
        return birdRepository.findBySexIs(Sex.valueOf(sex)).stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }

    @Override
    public List<BirdDTO> findOutcastBirds() {
        List<Bird> birds = birdRepository.findAll();
        List<Bird> outcastBirds = new ArrayList<>();
        for (Bird bird : birds){
            if(bird.getCage() == null){
                outcastBirds.add(bird);
            }
        }
        return outcastBirds.stream().map(BirdMapper::mapToBirdDto).collect(Collectors.toList());
    }

    private void findParents(BirdForPedigreeResponseDTO birdForPedigreeResponseDTO, int gen){
        if (gen < 2){
            Long birdId = Long.valueOf(birdForPedigreeResponseDTO.getBirdId());
            Bird bird = birdRepository.findById(birdId).orElseThrow(
                    () -> new BirdNotFoundException("Bird could not be found."));
            BirdReproduction birdReproduction = birdReproductionRepository.findByBirdAndReproductionRole(bird, ReproductionRole.CHILD);
            if(birdReproduction != null){
                ReproductionProcess reproductionProcess = birdReproduction.getReproductionProcess();
                Bird father = birdReproductionRepository
                        .findByReproductionProcessIdAndReproductionRole(reproductionProcess.getId(), ReproductionRole.FATHER)
                        .getBird();
                BirdForPedigreeResponseDTO fatherDTO = BirdMapper.mapToBirdForPedigreeResponseDTO(father);
                birdForPedigreeResponseDTO.setFather(fatherDTO);
                Bird mother = birdReproductionRepository
                        .findByReproductionProcessIdAndReproductionRole(reproductionProcess.getId(), ReproductionRole.MOTHER)
                        .getBird();
                BirdForPedigreeResponseDTO motherDTO = BirdMapper.mapToBirdForPedigreeResponseDTO(mother);
                birdForPedigreeResponseDTO.setMother(motherDTO);
            }

        }
    }

    private void findPedigree(BirdForPedigreeResponseDTO birdForPedigreeResponseDTO, int gen){
        if(gen < 2){
            findParents(birdForPedigreeResponseDTO, gen);
            if (birdForPedigreeResponseDTO.getFather() != null){
                findPedigree(birdForPedigreeResponseDTO.getFather(), gen + 1);
            }
            if(birdForPedigreeResponseDTO.getMother() != null){
                findPedigree(birdForPedigreeResponseDTO.getMother(), gen + 1);
            }
        }
    }

    private List<DescendantResponseDTO> findChildsList(BirdDetailReponseDTO birdDetailReponseDTO){
        Long birdId = Long.valueOf(birdDetailReponseDTO.getBirdId());
        Bird bird = birdRepository.findById(birdId).orElseThrow(
                () -> new BirdNotFoundException("Bird could not be found."));
        List<BirdReproduction> birdReproductions = birdReproductionRepository
                .findByBirdAndReproductionRoleNot(bird, ReproductionRole.CHILD);
        List<ReproductionProcess> reproductionProcesses = new ArrayList<>();
        for (BirdReproduction birdReproductionWalker : birdReproductions){
            reproductionProcesses.add(birdReproductionWalker.getReproductionProcess());
        }
        List<Bird> descendantsList = new ArrayList<>();
        List<BirdReproduction> descendantsReproductions;
        for (ReproductionProcess reproductionProcess : reproductionProcesses){
            descendantsReproductions = birdReproductionRepository
                    .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
            for (BirdReproduction descendantReproduction : descendantsReproductions){
                descendantsList.add(descendantReproduction.getBird());
            }
        }
        List<DescendantResponseDTO> descendantsDTOs = descendantsList.stream().map(BirdMapper::mapToDescendantResponseDTO)
                .collect(Collectors.toList());
        birdDetailReponseDTO.setDescendants(descendantsDTOs);
        return descendantsDTOs;
    }

//    private List<DescendantResponseDTO> findDescendantsList(BirdDetailReponseDTO birdDetailReponseDTO, int gen){
//        List<DescendantResponseDTO> descendantResponseDTOS = findChildsList(birdDetailReponseDTO);
//
//    }

    private void saveActDate(String ageRange, Bird bird){
        BirdReproduction birdReproduction = birdReproductionRepository
                .findByBirdAndReproductionRole(bird, ReproductionRole.CHILD);
        if(ageRange.equals("Chim chuyền")){
            birdReproduction.setActSwingBranch(new Date());
        }else {
            birdReproduction.setActAdultBirdDate(new Date());
        }
    }

}
