package org.hms.profilems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hms.profilems.dto.BloodGroup;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @Column(unique = true)
    private String email;
    private LocalDate dob;
    private String phone;
    private String address;

    @Column(unique = true)
    private String aadharNo;

    private BloodGroup bloodGroup;

    private Long profilePictureId;
    private String allergies;
    private String chronicDisease;



//        public Users toDTO() {
//        return new UserDTO(this.id, this.Name, this.email, this.password, this.role);
//    }
}
