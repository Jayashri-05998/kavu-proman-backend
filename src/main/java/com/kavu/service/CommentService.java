package com.kavu.service;

import com.kavu.model.Comment;
import com.kavu.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repo;

    public CommentService(CommentRepository repo) {
        this.repo = repo;
    }

    public List<Comment> getByTask(Long taskId) {
        return repo.findByTaskId(taskId);
    }

    public Comment add(Comment comment) {
        return repo.save(comment);
    }
}
