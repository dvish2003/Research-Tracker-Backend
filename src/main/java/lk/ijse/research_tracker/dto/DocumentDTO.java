package lk.ijse.research_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private UUID id;
    private ProjectDTO project;
    private String title;
    private String description;
    private String urlOrPath;
    private UserDTO uploadedBy;
    private LocalDateTime uploadedAt;
}
