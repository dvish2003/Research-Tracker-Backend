package lk.ijse.research_tracker.service.custom;



import lk.ijse.research_tracker.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUser();


    List<UserDTO> getUsers();


    int saveUser(UserDTO userDTO);

    int verifyUser(String email, String code);

    UserDTO searchUser(String username);
}