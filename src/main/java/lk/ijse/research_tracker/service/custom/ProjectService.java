package lk.ijse.research_tracker.service.custom;

import java.util.List;
import lk.ijse.research_tracker.dto.ProjectDTO;
import java.util.UUID;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();
    ProjectDTO getProjectById(UUID id);
    int createProject(ProjectDTO project);
    int updateProject(ProjectDTO project);
    int deleteProject(UUID id);
}
