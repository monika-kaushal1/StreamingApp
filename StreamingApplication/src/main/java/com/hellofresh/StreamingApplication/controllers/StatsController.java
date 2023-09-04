package com.hellofresh.StreamingApplication.controllers;

import com.hellofresh.StreamingApplication.domains.Stats;
import com.hellofresh.StreamingApplication.services.StatsService;
import com.hellofresh.StreamingApplication.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity<String> getStats() {
        try {
            Stats stats = statsService.getStats();
            String statsResponse = MapperUtil.statToResponseStrMapper(stats);
            return new ResponseEntity<>(statsResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
