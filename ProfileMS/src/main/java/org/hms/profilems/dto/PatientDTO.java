package org.hms.profilems.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;

    private String Name;


    private String email;
    private LocalDate dob;
    private String phone;
    private String address;


    private String aadharNo;

    private BloodGroup bloodGroup;

    private Long ProfilePictureId;

    private String allergies;
    private String chronicDisease;
}
