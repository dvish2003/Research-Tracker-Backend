package lk.ijse.research_tracker.dto;

import jakarta.persistence.*;
import lk.ijse.research_tracker.Entity.Document;
import lk.ijse.research_tracker.Entity.Milestone;
import lk.ijse.research_tracker.Entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectDTO {
    private UUID id;
    private String title;
    private String summary;
    private String status;
    private UserDTO pi;
    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
