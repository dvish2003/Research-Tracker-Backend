package lk.ijse.research_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private UUID id;
    private String email;
    private String name;
    private String password;
    private String Role;
    private Date createdAt;

}

