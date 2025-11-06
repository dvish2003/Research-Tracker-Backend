package lk.ijse.research_tracker.Entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String Role;
    @Column(nullable = false)
    private Date createdAt;
     @OneToMany(mappedBy = "pi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @OneToMany(mappedBy = "createdBy")
    private List<Milestone> milestones;

    @OneToMany(mappedBy = "uploadedBy")
    private List<Document> documents;

}
