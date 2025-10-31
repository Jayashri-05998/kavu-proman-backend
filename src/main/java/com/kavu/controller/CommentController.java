package com.kavu.controller;

import com.kavu.model.Comment;
import com.kavu.service.CommentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }
    
    @GetMapping("/{taskId}")
    public List<Comment> getByTask(@PathVariable Long taskId) {
        return service.getByTask(taskId);
    }

    @PostMapping
    public Comment add(@RequestBody Comment comment) {
        return service.add(comment);
    }
}
