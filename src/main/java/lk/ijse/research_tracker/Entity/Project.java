package lk.ijse.research_tracker.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String summary;

    private String status;

    @ManyToOne
    @JoinColumn(name = "pi_id")
    private User pi;

    @ElementCollection
    @CollectionTable(name = "project_tags", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "project_team_members", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "email")
    private List<String> teamMembers;

    private Date startDate;
    private Date endDate;

    private LocalDateTime createdAt;
    private String createdBy;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Milestone> milestones;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;
}
