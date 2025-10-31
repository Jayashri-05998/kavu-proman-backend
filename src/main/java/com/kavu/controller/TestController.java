package com.kavu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/secure/test")
    public String testSecureEndpoint() {
        return "This is a protected route for Admin or User.";
    }
}
