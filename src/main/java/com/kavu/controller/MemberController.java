package com.kavu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @GetMapping("/tasks")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEMBER')")
    public String memberTasks() {
        return "Welcome MEMBER! You can view and update assigned tasks.";
    }
}
