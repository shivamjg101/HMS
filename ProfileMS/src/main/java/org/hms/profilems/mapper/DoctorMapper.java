package org.hms.profilems.mapper;

import org.hms.profilems.dto.DoctorDTO;
import org.hms.profilems.entity.Doctor;


public class DoctorMapper {
    public static DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) return null;

        return new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getDob(),
                doctor.getPhone(),
                doctor.getAddress(),
                doctor.getLicenseNo(),
                doctor.getSpecialization(),
                doctor.getDepartment(),
                doctor.getTotalExp(),
                doctor.getGetProfilePictureId()
        );
    }

    public static Doctor toEntity(DoctorDTO doctorDTO) {
        if (doctorDTO == null) return null;

        return new Doctor(
                doctorDTO.getId(),
                doctorDTO.getName(),
                doctorDTO.getEmail(),
                doctorDTO.getDob(),
                doctorDTO.getPhone(),
                doctorDTO.getAddress(),
                doctorDTO.getLicenseNo(),
                doctorDTO.getSpecialization(),
                doctorDTO.getDepartment(),
                doctorDTO.getTotalExp(),
                doctorDTO.getProfilePictureId()
        );
    }
}
