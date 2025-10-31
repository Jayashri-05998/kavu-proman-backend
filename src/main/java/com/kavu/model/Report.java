package com.kavu.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String type;      // "project" or "task"
    private String title;
    private String user;
    private String status;    // "Completed", "In Progress", "Pending"

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;  // optional relationship to a project
}

