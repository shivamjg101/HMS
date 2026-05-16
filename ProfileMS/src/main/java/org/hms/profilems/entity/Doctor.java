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
public class Doctor {
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
    private String licenseNo;

    private String specialization;
    private String department;
    private Integer totalExp;
    private Long getProfilePictureId;

}
