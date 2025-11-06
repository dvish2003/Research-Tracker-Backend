package lk.ijse.research_tracker.service.custom.IMPL;

import lk.ijse.research_tracker.service.custom.ProjectService;
import lk.ijse.research_tracker.util.VarList;
import lk.ijse.research_tracker.dto.ProjectDTO;
import lk.ijse.research_tracker.repo.ProjectRepository;
import lk.ijse.research_tracker.repo.UserRepository;
import lk.ijse.research_tracker.Entity.Project;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lk.ijse.research_tracker.Entity.User;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectById(UUID id) {
        Project project = projectRepository.findById(id).orElse(null);
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public int createProject(ProjectDTO project)  {
        try {
            System.out.println("Create Project Service Impl" + project);

            Project projectEntity = modelMapper.map(project, Project.class);
            projectRepository.save(projectEntity);
            return VarList.OK; // Created
        } catch (Exception e) {
            return VarList.Internal_Server_Error; // Internal Server Error
        }
    }
@Override
    public int updateProject(ProjectDTO project) {
       try{
              if (projectRepository.existsById(project.getId())) {
                Project projectEntity = modelMapper.map(project, Project.class);
                projectRepository.save(projectEntity);
                return VarList.OK; // 200
              } else {
                return VarList.Not_Found; // 404
              }
         } catch (Exception e) {
              return VarList.Internal_Server_Error; // 500
       }
    }

    @Override
    public int deleteProject(UUID id) {
        // Implementation here
        return 0;
    }
}
