package org.hms.userms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hms.userms.dto.Roles;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @Column(unique = true)
    private String email;

    private String password;

    private Roles role;

    private Long profileId;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    // @Column(nullable = false)
    private LocalDateTime updatedAt;

//        public Users toDTO() {
//        return new UserDTO(this.id, this.Name, this.email, this.password, this.role);
//    }

    @PrePersist
    protected void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
