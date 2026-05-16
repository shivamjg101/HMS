package org.hms.profilems.mapper;

import org.hms.profilems.dto.PatientDTO;
import org.hms.profilems.entity.Patient;

public class PatientMapper {
    public static PatientDTO toDTO(Patient patient) {
        if (patient == null) return null;

        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDob(),
                patient.getPhone(),
                patient.getAddress(),
                patient.getAadharNo(),
                patient.getBloodGroup(),
                patient.getProfilePictureId(),
                patient.getAllergies(),
                patient.getChronicDisease()
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        if (patientDTO == null) return null;

        return new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getEmail(),
                patientDTO.getDob(),
                patientDTO.getPhone(),
                patientDTO.getAddress(),
                patientDTO.getAadharNo(),
                patientDTO.getBloodGroup(),
                patientDTO.getProfilePictureId(),
                patientDTO.getAllergies(),
                patientDTO.getChronicDisease()
        );
    }
}
