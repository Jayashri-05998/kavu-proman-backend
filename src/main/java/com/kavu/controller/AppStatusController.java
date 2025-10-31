package com.kavu.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AppStatusController {

    @GetMapping("/")
    public String home() {
        return "Server is running fine 🚀";
    }
}
