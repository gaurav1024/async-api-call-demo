package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CombinedDTO;
import com.example.demo.service.DashboardService;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/api/dashboard")
    public CombinedDTO getInfo() throws ExecutionException, InterruptedException {
        return dashboardService.getCombinedDTO();
    }
    
}
