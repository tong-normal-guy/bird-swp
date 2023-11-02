package com.example.birdReproductionManagement.dto.DashboardResponse;

import com.example.birdReproductionManagement.dto.BirdResponse.Bird4DashboardDTOResponse;
import com.example.birdReproductionManagement.dto.BirdResponse.BirdDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTOResponse {
    private int totalBird;
    private int totalMutation;
    private int totalProcess;
    private int totalEgg;
    private int totalUser;
    private int totalAdult;
    private int totalSwingbranch;
    private int totalBaby;
    private int totalCock;
    private int totalHen;
    private List<BirdDTO> top5Birds;
    private EggInWeekDTOResponse totalEggIn7Day;
    private List<CloseDateReproductionDTOResponse> bird_reproduction;
}
