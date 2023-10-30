package com.example.birdReproductionManagement.controller;

import com.example.birdReproductionManagement.service.SystemCheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final SystemCheckService systemCheckService;

    public ScheduledTasks(SystemCheckService systemCheckService) {
        this.systemCheckService = systemCheckService;
    }

    @Scheduled(cron = "0 0 12 * * ?") // Chạy vào 12h trưa hàng ngày
    public void performSystemCheckScheduled() {
        systemCheckService.performSystemCheck();
    }
}

