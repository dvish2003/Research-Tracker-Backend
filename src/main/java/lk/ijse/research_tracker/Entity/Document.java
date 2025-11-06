package lk.ijse.research_tracker.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document {

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

    private String urlOrPath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    private Date uploadedAt;
}
