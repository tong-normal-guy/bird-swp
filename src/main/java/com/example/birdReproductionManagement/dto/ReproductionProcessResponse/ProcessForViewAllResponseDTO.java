package com.example.birdReproductionManagement.dto.ReproductionProcessResponse;

import com.example.birdReproductionManagement.dto.BirdReproductionDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessForViewAllResponseDTO {
    private String processId;
    private String cageId;
    private String cockId;
    private String henId;
    private Boolean isDone;
    private Date pairingDate;
    private String birdTypeName;
    private List<BirdReproductionDTO> eggsList;
}
