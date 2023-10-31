package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdResponse.*;
import com.example.birdReproductionManagement.dto.ReproductionProcessDTO;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.BirdNotFoundException;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.ReproductionProcessNotFoundException;
import com.example.birdReproductionManagement.mapper.BirdMapper;
import com.example.birdReproductionManagement.mapper.BirdReproductionMapper;
import com.example.birdReproductionManagement.mapper.ReproductionProcessMapper;
import com.example.birdReproductionManagement.repository.*;
import com.example.birdReproductionManagement.service.BirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
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
        return birds.stream().map(BirdMapper::mapToBirdForListResponseDTO).collect(Collectors.toList());
    }

    @Override
    public BirdDetailReponseDTO getBirdDetailById(Long id) {
        Bird bird = birdRepository.findById(id).orElseThrow(
                () -> new BirdNotFoundException("Bird could not be found."));
        BirdDetailReponseDTO birdDetailReponseDTO = BirdMapper.mapToBirdDetailReponseDTO(bird);
        //Tìm cây phả hệ của chim
        BirdForPedigreeResponseDTO birdForPedigreeResponseDTO = BirdMapper.mapToBirdForPedigreeResponseDTO(bird);
        int gen = 1;
        findPedigree(birdForPedigreeResponseDTO, gen);
        birdDetailReponseDTO.setFather(birdForPedigreeResponseDTO.getFather());
        birdDetailReponseDTO.setMother(birdForPedigreeResponseDTO.getMother());
        //Tìm danh sách các thế hệ sau của chim
        List<DescendantResponseDTO> descendantResponseDTOS = new ArrayList<>();
        findDescendantsList(descendantResponseDTOS, bird, 1);
        birdDetailReponseDTO.setDescendants(descendantResponseDTOS);
        //Tìm ngày dự kiến các giai đoạn dự kiến
//        BirdReproduction birdReproduction = birdReproductionRepository
//                .findByBirdAndReproductionRole(bird, ReproductionRole.CHILD);
//        if(birdReproduction != null){
//            birdDetailReponseDTO.setBirdReproduction(BirdReproductionMapper
//                    .mapToBirdReproductionForBirdDetailResponseDTO(birdReproduction));
//        }
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
        String ageRange = bird.getAgeRange();
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
            if (!ageRange.equals(birdDto.getAgeRange())){
                if(birdDto.getAgeRange().equals("Chuyền")){
                    finalBird.setSwingBranchDate(new Date());
                }else if (birdDto.getAgeRange().equals("Trưởng thành")){
                    finalBird.setAdultBirdDate(new Date());
                    if(birdReproductionRepository.existsByBirdAndReproductionProcessIsDone(bird, false)){
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
//                                if(birdReproductionRepository
//                                        .existsByReproductionProcessAndReproductionRoleAndBirdAgeRange(
//                                                reproductionProcess, ReproductionRole.CHILD, "Non")
//                                        || birdReproductionRepository
//                                        .existsByReproductionProcessAndReproductionRoleAndBirdAgeRange(
//                                                reproductionProcess, ReproductionRole.CHILD, "Chuyền")){
//                                    flag = true;
//                                }
//                                flag = birdReproductionRepository.existsByReproductionProcessAndReproductionRoleAndBirdAgeRangeNot(reproductionProcess, ReproductionRole.CHILD, "Trưởng thành");
                                if (!flag){
                                    reproductionProcess.setIsDone(true);
                                    reproductionProcessRepository.save(reproductionProcess);
                                }
                            }
                    }
                }

            }

        }
//        Cage cage = null;
        //Kiểm tra chim non đã trưởng thành hết chưa để kết thúc process
        if(birdDto.getCageId() != null && !birdDto.getCageId().isEmpty()){
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

    @Override
    public String findBirds() {
        long id = 6;
        ReproductionProcess reproductionProcess = reproductionProcessRepository.findById(id).orElseThrow(() -> new ReproductionProcessNotFoundException("ahsdgasgd"));
        boolean flag;
        flag = birdReproductionRepository.existsByReproductionProcessAndReproductionRoleAndBirdAgeRangeNot(reproductionProcess, ReproductionRole.CHILD, "Trưởng thành");
        if (flag){
            return "có tồn tại";
        }
        return "không tồn tại";
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

    private List<DescendantResponseDTO> findChildList(Bird bird, int gen){
            List<ReproductionProcess> reproductionProcesses = reproductionProcessRepository
                    .findByBirdAndReproductionRoleNot(bird, ReproductionRole.CHILD);
            List<Bird> childList = new ArrayList<>();
            for (ReproductionProcess reproductionProcess : reproductionProcesses){
                List<Bird> birds = birdRepository
                        .findByReproductionProcessAndReproductionRole(reproductionProcess, ReproductionRole.CHILD);
                childList.addAll(birds);
            }
            if (!childList.isEmpty()){
                List<DescendantResponseDTO> descendantResponseDTOS = childList.stream()
                        .map(BirdMapper::mapToDescendantResponseDTO)
                        .collect(Collectors.toList());
                for (DescendantResponseDTO descendantResponseDTO : descendantResponseDTOS){
                    descendantResponseDTO.setGeneration(String.valueOf(gen));
                }
                return descendantResponseDTOS;
            }
            return null;
    }

    private void findDescendantsList(List<DescendantResponseDTO> descendantResponseDTOS, Bird bird, int gen){
        if (birdReproductionRepository
                .existsByBirdAndReproductionRoleNotAndReproductionRoleNot(bird, ReproductionRole.EGG, ReproductionRole.CHILD)){
            List<DescendantResponseDTO> childList = findChildList(bird, gen);
            if(childList != null){
                descendantResponseDTOS.addAll(childList);
                for (DescendantResponseDTO descendantResponseDTO : childList){
                    Bird child = birdRepository.findById(Long.valueOf(descendantResponseDTO.getBirdId()))
                            .orElseThrow(() -> new BirdNotFoundException("Bird could not be found."));
                    findDescendantsList(descendantResponseDTOS, child, gen + 1);
                }
            }
        }
    }

}
