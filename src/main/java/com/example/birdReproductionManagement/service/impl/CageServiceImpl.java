package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.dto.CageResponse.Cage4ListDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.UserDTO;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.*;
import com.example.birdReproductionManagement.mapper.*;
import com.example.birdReproductionManagement.repository.*;
import com.example.birdReproductionManagement.service.CageService;
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
public class CageServiceImpl implements CageService {
    private final CageRepository cageRepository;
    private final BirdRepository birdRepository;
    private final ReproductionProcessRepository reproductionProcessRepository;
    private final BirdReproductionRepository birdReproductionRepository;
    private final UserRepository userRepository;

//    private

    @Override
    public List<CageDTO> viewCageUsable() {
        List<Cage> cages = cageRepository.findAllByQuantity(0);
        return cages.stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
    }

    @Override
    public List<CageDTO> findAllCages() {
        List<Cage> cages = cageRepository.findAll();
//        for (Cage cage : cages){
//            cage.setQuantity(birdRepository.countBirdByCage(cage));
//            cageRepository.save(cage);
//        }
        return cages.stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
    }
//    @Override
//    public List<?> pickaCages(Boolean process) {
//        List<Cage> cages = new ArrayList<>();
//        List<CageDetailDTOResponse> cageDetailDTOResponses = new ArrayList<>();
//        List<Cage4ListDTO> cage4ListDTOs = new ArrayList<>();
//        int bSize = 0;
//        int eSize = 0;
//        int efSize = 0;
//        //check condition, have check process or no. start
//        if (process){
////        //check process
//            cages = cageRepository.findCagesWithLocationStartingWithB();
//        } else {
////        //no check process
//            cages = cageRepository.findAll();
////        cages = cageRepository.findAll();
////        cage4ListDTOs = cages.stream().map(CageMapper::map2Cage4ListDTO).collect(Collectors.toList());
////        //check condition hv process or not. end
////        return cage4ListDTOs;
//        }
//        // find all cages that:
//        for (Cage cage : cages) {
//            bSize = 0;
//            eSize = 0;
//            efSize = 0;
//            CageDetailDTOResponse cageDetailDTOResponse = new CageDetailDTOResponse();
//            // entity start
//            ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage_Id(cage.getId()).orElse(null);
//            if (reproductionProcess != null){
//                List<BirdReproduction> birdReproductions = reproductionProcess.getBirdReproductions();
//
//                //count egg
//                for (BirdReproduction birdReproduction: birdReproductions) {
//                    if (birdReproduction.getReproductionRole().equals(ReproductionRole.EGG) ||
//                            birdReproduction.getReproductionRole().equals(ReproductionRole.CHILD)) {
//                        eSize++;
//                        if (birdReproduction.getEggStatus().toLowerCase().equals("hỏng")) {
//                            efSize++;
//                        }
//                    }
//                    if (birdReproduction.getReproductionRole().equals(ReproductionRole.CHILD) ||
//                            birdReproduction.getReproductionRole().equals(ReproductionRole.MOTHER) ||
//                            birdReproduction.getReproductionRole().equals(ReproductionRole.FATHER)) {
//                        bSize++;
//                    }
//                }
//                reproductionProcess.setFailEgg(efSize);
//                reproductionProcess.setTotalEgg(eSize);
//                birdReproductionRepository.findAllByReproductionProcess_Id(reproductionProcess.getId());
//                // entity end
//                // dto
//                Reproduction4CageDetailDTOResponse reproduction4CageDetailDTOResponse = ReproductionProcessMapper.map2Reproduction4CageDetailDTO(reproductionProcess);
//                List<BirdRe4CageDetailDTOResponse> bird4CageDetailDTOResponses = new ArrayList<>();
//
//                for (BirdReproduction birdReproduction:birdReproductions) {
//                    BirdRe4CageDetailDTOResponse bird4CageDetailDTOResponse = BirdReproductionMapper.map2Bird4CageDetailDTO(birdReproduction);
//                    if((birdReproduction.getBird() != null) ){
//                        bird4CageDetailDTOResponse.setBird(BirdMapper.map2Birdd4CageDetailDTO(birdReproduction.getBird()));
//                    }
//                    bird4CageDetailDTOResponses.add(bird4CageDetailDTOResponse);
//                }
//                //mapper 4 process start
////                    eSize = bird4CageDetailDTOResponses.size();
//                cageDetailDTOResponse.setBirdReproduction(bird4CageDetailDTOResponses);
//                cageDetailDTOResponse.setReproductionProcess(reproduction4CageDetailDTOResponse);
//                //mapper 4 process end
//            }else {
//                if (cage.getBirdList().isEmpty()){
//                    cageDetailDTOResponse.setBird(cage.getBirdList()
//                            .stream().map(BirdMapper::map2Birdd4CageDetailDTO).collect(Collectors.toList()));
//                    bSize = cage.getBirdList().size();
//                }
//            }
//            // mapper to CageDetailDTOResponse
//            if(cage.getUser() != null){
//                User4CageDetailDTOResponse user4CageDetailDTOResponse = UserMapper.map2User4CageDetailDTO(cage.getUser());
//                cageDetailDTOResponse.setUser(user4CageDetailDTOResponse);
//            }
//            cageDetailDTOResponse.setCageId(String.valueOf(cage.getId()));
//            cageDetailDTOResponse.setLocation(cage.getLocation());
//            if (cage.getAvailable() != null)
//                cageDetailDTOResponse.setAvailable(cage.getAvailable());
//            else cageDetailDTOResponse.setAvailable(false);
//            cageDetailDTOResponse.setQuantity(bSize);
//            cageDetailDTOResponses.add(cageDetailDTOResponse);
//        }
//        return cageDetailDTOResponses;
//    }
    @Override
    public List<CageDetailDTOResponse> pickaCages(Boolean process) {
        List<Cage> cages = new ArrayList<>();
        //check condition, have check process or no. start
        if (!process){
            //no check process
            cages = cageRepository.findAll();
        }else {
            //check process
            cages = cageRepository.findCagesWithLocationStartingWithB();
        }
        //check condition hv process or not. end
        List<CageDetailDTOResponse> cageDetailDTOResponses = new ArrayList<>();
        // find all cages that:
        for (Cage cage : cages) {
            int bSize = 0;
            int eSize = 0;
            int efSize = 0;
            CageDetailDTOResponse cageDetailDTOResponse = new CageDetailDTOResponse();
            // entity start
            ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage_Id(cage.getId()).orElse(null);
            if (reproductionProcess != null){
                List<BirdReproduction> birdReproductions = reproductionProcess.getBirdReproductions();

                //count egg
                for (BirdReproduction birdReproduction: birdReproductions) {
                    if (birdReproduction.getReproductionRole().equals(ReproductionRole.EGG)){
                        eSize++;
                        if (birdReproduction.getEggStatus().toLowerCase().equals("broken") || birdReproduction.isFail() == true) {
                            efSize++;
                        }
                    } else if (birdReproduction.getReproductionRole().equals(ReproductionRole.CHILD) ||
                            birdReproduction.getReproductionRole().equals(ReproductionRole.MOTHER) ||
                            birdReproduction.getReproductionRole().equals(ReproductionRole.FATHER)) {
                        bSize++;
                    }
                }
                reproductionProcess.setFailEgg(efSize);
                reproductionProcess.setTotalEgg(eSize);
//                        birdReproductionRepository.findAllByReproductionProcess_Id(reproductionProcess.getId());
                // entity end
                // dto
                Reproduction4CageDetailDTOResponse reproduction4CageDetailDTOResponse = ReproductionProcessMapper.map2Reproduction4CageDetailDTO(reproductionProcess);
                List<BirdRe4CageDetailDTOResponse> bird4CageDetailDTOResponses = new ArrayList<>();

                for (BirdReproduction birdReproduction:birdReproductions) {
                    BirdRe4CageDetailDTOResponse bird4CageDetailDTOResponse = BirdReproductionMapper.map2Bird4CageDetailDTO(birdReproduction);
                    if((birdReproduction.getBird() != null) ){
                        bird4CageDetailDTOResponse.setBird(BirdMapper.map2Birdd4CageDetailDTO(birdReproduction.getBird()));
                    }
                    bird4CageDetailDTOResponses.add(bird4CageDetailDTOResponse);
                }
                //mapper start
//                eSize = bird4CageDetailDTOResponses.size();
                cageDetailDTOResponse.setBirdReproduction(bird4CageDetailDTOResponses);
                cageDetailDTOResponse.setReproductionProcess(reproduction4CageDetailDTOResponse);
                //mapper end
            } else {
                if (!cage.getBirdList().isEmpty()){
                    cageDetailDTOResponse.setBird(cage.getBirdList()
                            .stream().map(BirdMapper::map2Birdd4CageDetailDTO).collect(Collectors.toList()));
                    bSize = cage.getBirdList().size();
                }
            }

            // mapper to CageDetailDTOResponse
            if(cage.getUser() != null){
                User4CageDetailDTOResponse user4CageDetailDTOResponse = UserMapper.map2User4CageDetailDTO(cage.getUser());
                cageDetailDTOResponse.setUser(user4CageDetailDTOResponse);
            }
            cageDetailDTOResponse.setCageId(String.valueOf(cage.getId()));
            cageDetailDTOResponse.setLocation(cage.getLocation());
            if (cage.getAvailable() != null)
                cageDetailDTOResponse.setAvailable(cage.getAvailable());
            else cageDetailDTOResponse.setAvailable(false);
            cageDetailDTOResponse.setQuantity(bSize);
            cageDetailDTOResponses.add(cageDetailDTOResponse);
        }
        return cageDetailDTOResponses;
    }
    @Override
    public CageDetailDTOResponse getDetailById(Long id) {
        Cage cage = cageRepository.findById(id).orElseThrow(()
                -> new CageNotFoundException("Cage could not be found."));

        CageDetailDTOResponse cageDto = CageMapper.mapToCageDetailDTOResponse(cage);
        if (cage.getLocation().contains("B")){
            ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage(cage);
            if (reproductionProcess != null){
                Reproduction4CageDetailDTOResponse reproductionProcessDTO = ReproductionProcessMapper
                        .map2Reproduction4CageDetailDTO(reproductionProcess);
                cageDto.setReproductionProcess(reproductionProcessDTO);
                //Cách cũ
//                List<BirdReproduction> birdReproductions = reproductionProcess.getBirdReproductions();
                //Cách mới
                List<BirdReproduction> birdReproductions = birdReproductionRepository
                        .findByReproductionRoleNotEGG(reproductionProcess, cage);
                birdReproductions.addAll(birdReproductionRepository.findByReproductionRoleEGG(reproductionProcess));
                if (birdReproductions != null){
                    cageDto.setBirdReproduction(birdReproductions.stream()
                            .map(BirdReproductionMapper::map2Bird4CageDetailDTO).collect(Collectors.toList()));
                }
            }
        }else if (cage.getLocation().contains("A")){
            List<Bird> birds = cage.getBirdList();
            if(birds != null){
                cageDto.setBird(birds.stream().map(BirdMapper::map2Birdd4CageDetailDTO).collect(Collectors.toList()));
            }
        }
        if (cage.getUser() != null){
            User user = cage.getUser();
            cageDto.setUser(UserMapper.map2User4CageDetailDTO(user));
        }

        return cageDto;
    }

    @Override
    public CageDTO addCage(CageDTO cageDto) {
        cageDto.setQuantity(0);
        cageDto.setAvailable(true);
        Cage cage = cageRepository.save(CageMapper.mapToCage(cageDto));
        cage.setLocation(cage.getLocation() + cage.getId());
        if (cageDto.getUserId() != null){
            User user = userRepository.findById(Long.valueOf(cageDto.getUserId())).orElseThrow(
                    () -> new UserNotFoundException("User could not be found."));
            cage.setUser(user);
        }
        return CageMapper.mapToCageDto(cageRepository.save(cage));
    }


    @Override
    public CageDTO updateCage(Long id, CageDTO cageDto) {
        Cage cage = cageRepository.findById(id).orElseThrow(()
                -> new CageNotFoundException("Cage could not be updated."));
        Cage updatedCage = CageMapper.mapToCage(cageDto);
        updatedCage.setId(id);
        return CageMapper.mapToCageDto(cageRepository.save(CageMapper.mapToCage(cageDto)));
    }

    @Override
    public CageDTO updateCageByFields(Long id, CageDTO cageDto) {
        Cage cage = cageRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User could not be found in updateCageByFields."));
        if(cage.getAvailable() != cageDto.getAvailable()){
            if (birdRepository.countBirdByCage(cage) > 0)
                throw new InternalException("There is bird in this cage.");
        }
        Cage finalCage = cage;
        ReflectionUtils.doWithFields(cageDto.getClass(), field -> {
            field.setAccessible(true);
            Object newValue = field.get(cageDto);
            if(newValue != null){
                String fieldName = field.getName();
                if(!fieldName.equals("location") && !fieldName.equals("userId")){
                    Field existingField = ReflectionUtils.findField(finalCage.getClass(), fieldName);
                    if(existingField != null){
                        existingField.setAccessible(true);
                        ReflectionUtils.setField(existingField, finalCage, newValue);
                    }
                }
            }
        });
        if(cageDto.getLocation() != null){
            String newLocation = cageDto.getLocation() + cage.getId();
            finalCage.setLocation(newLocation);
        }
        if(cageDto.getUserId() != null && !cageDto.getUserId().isEmpty()){
            User user = userRepository.findById(Long.valueOf(cageDto.getUserId())).orElseThrow(
                    () -> new UserNotFoundException("User could not be found."));
            finalCage.setUser(user);
        }
        cage = finalCage;
        return CageMapper.mapToCageDto(cageRepository.save(cage));
    }

    @Override
    public void deleteCage(Long id) {
        Cage cage = cageRepository.findById(id).orElseThrow(()
                -> new CageNotFoundException("Cage could not be deleted."));
        cageRepository.delete(cage);
    }

    @Override
    public List<CageDTO> findByLocation(String location, boolean available) {
        if(!location.equals("A")){
            return cageRepository.findByLocationContainsAndAvailableIsTrueAndQuantityEquals(location, 0)
                    .stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
        }
        return cageRepository.findByLocationContainsAndAvailableIsTrue(location).stream().map(CageMapper::mapToCageDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CageDetailDTOResponse> viewCageByLocation(String location) {
        List<Cage> cages = cageRepository.findByLocationContains(location);
        List<CageDetailDTOResponse> cageDetailDTOResponses = new ArrayList<>();
        for (Cage cage : cages){
            CageDetailDTOResponse cageDetailDTOResponse = CageMapper.mapToCageDetailDTOResponse(cage);
            if(cage.getUser() != null){
                User4CageDetailDTOResponse user = UserMapper.map2User4CageDetailDTO(cage.getUser());
                cageDetailDTOResponse.setUser(user);
            }
            List<Bird> birds = birdRepository.findByCage_Id(cage.getId());
            List<Bird4CageDetailDTOResponse> bird4CageDetailDTOResponses = birds.stream()
                    .map(BirdMapper::map2Birdd4CageDetailDTO).collect(Collectors.toList());
            cageDetailDTOResponse.setBird(bird4CageDetailDTOResponses);
            cageDetailDTOResponses.add(cageDetailDTOResponse);
        }
        return cageDetailDTOResponses;
    }

    @Override
    public BirdDTO addBirdToCage(Long cageId, BirdDTO birdDTO) {
        Cage cage = cageRepository.findById(cageId).orElseThrow(
                () -> new CageNotFoundException("Cage could not be found in addBirdToCage."));
        Long id = Long.valueOf(birdDTO.getBirdId());
        Bird bird = birdRepository.findById(id).orElseThrow(
                () -> new BirdNotFoundException("Bird could not be found in addBirdToCage."));
        cage.setQuantity(cage.getQuantity() + 1);
        cageRepository.save(cage);
        bird.setCage(cage);
        return BirdMapper.mapToBirdDto(birdRepository.save(bird));
    }
//    @Override
//    public List<Cage4ListDTO> listCage() {
//        List<Cage4ListDTO> cages = cageRepository.findAll().stream().map(CageMapper::map2Cage4ListDTO).collect(Collectors.toList());
//        for (Cage4ListDTO cageDTO: cages) {
//            User user = userRepository.findUserByCageId(Long.valueOf(cageDTO.getCageId()));
//            if (user != null){
//                UserDTO userDTO = UserMapper.mapToUserDto(user);
//                cageDTO.setUser(userDTO);
//            }
//        }
//        return cages;
//    }
}
