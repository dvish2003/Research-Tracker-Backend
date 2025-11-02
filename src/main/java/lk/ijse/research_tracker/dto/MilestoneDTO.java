package lk.ijse.research_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDTO {
    private UUID id;
    private ProjectDTO project;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;
    private UserDTO createdBy;
}
