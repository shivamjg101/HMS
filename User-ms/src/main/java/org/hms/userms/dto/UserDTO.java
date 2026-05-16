package org.hms.userms.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String Name;

    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    private Roles role;

    private Long profileId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public Users toEntity() {
//        return new UserDTO(this.id, this.Name, this.email, this.password, this.role);
//    }
}
