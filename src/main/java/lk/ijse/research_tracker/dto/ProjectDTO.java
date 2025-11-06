package lk.ijse.research_tracker.dto;

import lk.ijse.research_tracker.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private UUID id;
    private String title;
    private String summary;
    private String status;
    private UserDTO pi; 
    private List<String> tags;
    private List<String> teamMembers;
    private Date startDate;
    private Date endDate;
    private LocalDateTime createdAt;
    private String createdBy;
}
