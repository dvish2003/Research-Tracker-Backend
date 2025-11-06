package lk.ijse.research_tracker.controller;

import lk.ijse.research_tracker.dto.AuthDTO;
import lk.ijse.research_tracker.dto.ResponseDTO;
import lk.ijse.research_tracker.dto.UserDTO;
import lk.ijse.research_tracker.repo.UserRepository;
import lk.ijse.research_tracker.service.custom.UserService;
import lk.ijse.research_tracker.util.JwtUtil;
import lk.ijse.research_tracker.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // constructor injection
    public UserController(UserService userService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "test";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        System.out.println("register");
        try {
            System.out.println(userDTO);
            Date sqlDate = Date.valueOf(LocalDate.now());
            userDTO.setCreatedAt(sqlDate);
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/allUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            List<UserDTO> userList = userService.getAllUser();

            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", userList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<ResponseDTO> getUser(@RequestParam String email) {
        UserDTO userDTO = userService.searchUser(email);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "User Not Found", null));
        }
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Success", userDTO));
    }

}
