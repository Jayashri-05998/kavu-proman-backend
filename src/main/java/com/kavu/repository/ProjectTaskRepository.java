package com.kavu.repository;

import com.kavu.model.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
    List<ProjectTask> findByProjectId(Long projectId);
}
