package com.example.birdReproductionManagement.service.impl;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import com.example.birdReproductionManagement.dto.BirdReproductionResponse.BirdRe4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.Bird4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import com.example.birdReproductionManagement.dto.BirdTypeDTO;
import com.example.birdReproductionManagement.dto.CageResponse.CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.CageResponse.CageDTO;
import com.example.birdReproductionManagement.dto.ReproductionProcessResponse.Reproduction4CageDetailDTOResponse;
import com.example.birdReproductionManagement.dto.UserResponse.User4CageDetailDTOResponse;
import com.example.birdReproductionManagement.entity.*;
import com.example.birdReproductionManagement.exceptions.CageNotFoundException;
import com.example.birdReproductionManagement.exceptions.UserNotFoundException;
import com.example.birdReproductionManagement.mapper.*;
import com.example.birdReproductionManagement.repository.BirdRepository;
import com.example.birdReproductionManagement.repository.BirdReproductionRepository;
import com.example.birdReproductionManagement.repository.CageRepository;
import com.example.birdReproductionManagement.repository.ReproductionProcessRepository;
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

//    private

    @Override
    public List<CageDTO> viewCageUsable() {
        List<Cage> cages = cageRepository.findAllByQuantity(0);
        return cages.stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
    }

    @Override
    public List<CageDTO> findAllCages() {
        return cageRepository.findAll().stream().map(CageMapper::mapToCageDto).collect(Collectors.toList());
    }
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
            CageDetailDTOResponse cageDetailDTOResponse = new CageDetailDTOResponse();
            // entity start
            ReproductionProcess reproductionProcess = reproductionProcessRepository.findByIsDoneFalseAndCage_Id(cage.getId()).orElse(null);
            if (reproductionProcess != null){
                List<BirdReproduction> birdReproductions = birdReproductionRepository.findAllByReproductionProcess_Id(reproductionProcess.getId());

//                List<BirdReproduction> eggReproductions = birdReproductionRepository.findAllEggsByReproductionProcessId(reproductionProcess.getId());
//                List<BirdReproduction> parentReproductions = birdReproductionRepository.findAllParentsByReproductionProcessId(reproductionProcess.getId());
            // entity end

                // dto
                Reproduction4CageDetailDTOResponse reproduction4CageDetailDTOResponse = ReproductionProcessMapper.map2Reproduction4CageDetailDTO(reproductionProcess);
                List<BirdRe4CageDetailDTOResponse> bird4CageDetailDTOResponses = new ArrayList<>();

                for (BirdReproduction birdReproduction:birdReproductions) {
                    BirdRe4CageDetailDTOResponse bird4CageDetailDTOResponse = BirdReproductionMapper.map2Bird4CageDetailDTO(birdReproduction);
                    if((birdReproduction.getBird() != null) ){
                        bird4CageDetailDTOResponse.setBird(BirdMapper.map2Birdd4CageDetailDTO(birdReproduction.getBird()));
                        if (birdReproduction.getReproductionRole() == ReproductionRole.EGG || birdReproduction.getReproductionRole() == ReproductionRole.CHILD){
                            MyUtils.expDateByLaidDate4BirdReproduct(bird4CageDetailDTOResponse,birdReproduction.getBird().getBirdType());
                        }
                    }
                    bird4CageDetailDTOResponses.add(bird4CageDetailDTOResponse);
                }
                //mapper start

                cageDetailDTOResponse.setBirdReproduction(bird4CageDetailDTOResponses);
                cageDetailDTOResponse.setReproductionProcess(reproduction4CageDetailDTOResponse);
                //mapper end
            } else {
                List<Bird> birds = birdRepository.findByCage_Id(cage.getId());
                List<Bird4CageDetailDTOResponse> birdDTO = birds.stream().map(BirdMapper::map2Birdd4CageDetailDTO).collect(Collectors.toList());
                cageDetailDTOResponse.setBird(birdDTO);
            }

            // mapper to CageDetailDTOResponse
            User4CageDetailDTOResponse user4CageDetailDTOResponse = UserMapper.map2User4CageDetailDTO(cage.getUser());
            cageDetailDTOResponse.setUser(user4CageDetailDTOResponse);
            cageDetailDTOResponse.setCageId(String.valueOf(cage.getId()));
            cageDetailDTOResponse.setLocation(cage.getLocation());
            cageDetailDTOResponse.setAvailable(cage.getAvailable());
            cageDetailDTOResponse.setQuantity(cage.getQuantity());
            cageDetailDTOResponses.add(cageDetailDTOResponse);
        }
        return cageDetailDTOResponses;

    }

    @Override
    public CageDTO getDetailById(Long id) {
        Cage cage = cageRepository.findById(id).orElseThrow(()
                -> new CageNotFoundException("Cage could not be found."));
        CageDTO cageDto = CageMapper.mapToCageDto(cage);
        cageDto.setBirdList(birdRepository.findByCage_Id(cage.getId()).stream()
                .map(BirdMapper::mapToBirdDto).collect(Collectors.toList()));
        return cageDto;
    }

    @Override
    public CageDTO addCage(CageDTO cageDto) {
        cageDto.setQuantity(0);
        cageDto.setAvailable(true);
        Cage cage = cageRepository.save(CageMapper.mapToCage(cageDto));
        cage.setLocation(cage.getLocation() + cage.getId());
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
        Cage finalCage = cage;
        ReflectionUtils.doWithFields(cageDto.getClass(), field -> {
            field.setAccessible(true);
            Object newValue = field.get(cageDto);
            if(newValue != null){
                String fieldName = field.getName();
                Field existingField = ReflectionUtils.findField(finalCage.getClass(), fieldName);
                if(existingField != null){
                    existingField.setAccessible(true);
                    ReflectionUtils.setField(existingField, finalCage, newValue);
                }
            }
        });
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
        if(!available){
            return cageRepository.findByLocationContains(location).stream().map(CageMapper::mapToCageDto)
                    .collect(Collectors.toList());
        }
        return cageRepository.findByLocationContainsAndAvailableIsTrueAndQuantityEquals(location, 0).stream().map(CageMapper::mapToCageDto)
                .collect(Collectors.toList());
    }
}
