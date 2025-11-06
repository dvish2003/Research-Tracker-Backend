package lk.ijse.research_tracker.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.research_tracker.dto.ResponseDTO;
import lk.ijse.research_tracker.dto.UserDTO;
import lk.ijse.research_tracker.repo.UserRepository;
import lk.ijse.research_tracker.service.custom.ProjectService;
import lk.ijse.research_tracker.service.custom.UserService;
import lk.ijse.research_tracker.util.JwtUtil;
import lk.ijse.research_tracker.util.VarList;
import lk.ijse.research_tracker.dto.ProjectDTO;
import lk.ijse.research_tracker.Entity.User;
import lk.ijse.research_tracker.Entity.Project;
import lk.ijse.research_tracker.repo.ProjectRepository;





@RestController
@RequestMapping("api/v1/project")
@CrossOrigin
public class ProjectController {
    
    private final ProjectService projectService;
    private final  UserService userService;
        private final UserRepository userRepository;
        private final ProjectRepository projectRepository;

    // constructor injection
    public ProjectController(ProjectService projectService, UserService userService, UserRepository userRepository, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;

        }

        

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveProject(@RequestBody ProjectDTO projectDTO) {
        System.out.println("Save Project");
        try {
            System.out.println(projectDTO);
            UserDTO userDTO = userService.searchUser(projectDTO.getCreatedBy());
            projectDTO.setPi(userDTO);
            int res = projectService.createProject(projectDTO);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(200)
                            .body(new ResponseDTO(VarList.OK, "Project Created Successfully", null));
                }
                
                default -> {
                    return ResponseEntity.status(500)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", e.getMessage()));
        }
    }

    @GetMapping(value = "/getAllProjects")
    public ResponseEntity<ResponseDTO> getAllProjects() {
        try {
            List<ProjectDTO> projects = projectService.getAllProjects();
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "Projects Retrieved Successfully", projects));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", e.getMessage()));
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateProject(@RequestBody ProjectDTO projectDTO) {
            System.out.println("update ........................................"+projectDTO);
        try {
            int res = projectService.updateProject(projectDTO);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(200)
                            .body(new ResponseDTO(VarList.OK, "Project Updated Successfully", null));
                }
                
                default -> {
                    return ResponseEntity.status(404)
                            .body(new ResponseDTO(VarList.Not_Found, "Project Not Found", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", e.getMessage()));
        }
    }
    }
