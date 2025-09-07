package com.sujay.journalApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//First API Creation
@RestController
@RequestMapping("/health")
public class HealthCheck {
    @GetMapping("/check")
    public String healthCheck() {
        return "Ok - Working fine!!!";
    }
}