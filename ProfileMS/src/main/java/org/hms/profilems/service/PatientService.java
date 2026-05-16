package org.hms.profilems.service;

import org.hms.profilems.dto.DoctorDropdown;
import org.hms.profilems.dto.PatientDTO;
import org.hms.profilems.exception.HmsException;

import java.util.List;

public interface PatientService {
    public Long addPatient(PatientDTO patientDTO) throws HmsException;

    public PatientDTO getPatientById(Long id) throws HmsException;

    public PatientDTO updatePatient(PatientDTO patientDTO) throws HmsException;

    public Boolean patientExists(Long id) throws HmsException;

    public List<PatientDTO> getAllPatients() throws HmsException;

    public List<DoctorDropdown> getPatientsById(List<Long> ids) throws HmsException;

}
