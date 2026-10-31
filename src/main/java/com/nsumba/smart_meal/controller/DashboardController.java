package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // Bonus
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(){
        Map<String, Long> summary = dashboardService.getSummary();
        return ResponseEntity.status(HttpStatus.OK).body(summary);
    }
}
