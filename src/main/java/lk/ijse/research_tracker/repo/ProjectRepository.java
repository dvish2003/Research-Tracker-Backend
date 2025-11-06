package lk.ijse.research_tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.research_tracker.Entity.Project;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    
    
}
