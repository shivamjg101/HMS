package org.hms.profilems.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
   private Long id;

    private String Name;


    private String email;
    private LocalDate dob;
    private String phone;
    private String address;


    private String licenseNo;

    private String specialization;
    private String department;
    private Integer totalExp;


   private Long ProfilePictureId;
}
