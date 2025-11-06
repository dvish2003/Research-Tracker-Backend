package lk.ijse.research_tracker.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "milestones")
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private Date dueDate;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
