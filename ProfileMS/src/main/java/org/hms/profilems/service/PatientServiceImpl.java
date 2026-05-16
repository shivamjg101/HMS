package org.hms.profilems.service;

import org.hms.profilems.dto.DoctorDropdown;
import org.hms.profilems.dto.PatientDTO;
import org.hms.profilems.entity.Patient;
import org.hms.profilems.exception.HmsException;
import org.hms.profilems.mapper.PatientMapper;
import org.hms.profilems.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService  {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Long addPatient(PatientDTO patientDTO) throws HmsException {
        System.out.println(patientDTO);

        if (patientDTO.getEmail() != null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) {
            throw new HmsException("PATIENT_ALREADY_EXISTS");
        }

        if (patientDTO.getAadharNo() != null && patientRepository.findByAadharNo(patientDTO.getAadharNo()).isPresent()) {
            throw new HmsException("PATIENT_ALREADY_EXISTS");
        }

        Patient savedPatient = patientRepository.save(PatientMapper.toEntity(patientDTO));
        return savedPatient.getId();
    }

    @Override
    public PatientDTO getPatientById(Long id) throws HmsException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new HmsException("PATIENT_NOT_FOUND"));

        return PatientMapper.toDTO(patient);
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws HmsException {
        patientRepository.findById(patientDTO.getId())
                .orElseThrow(() -> new HmsException("PATIENT_NOT_FOUND"));

        Patient savedPatient = patientRepository.save(PatientMapper.toEntity(patientDTO));
        return PatientMapper.toDTO(savedPatient);
    }

    @Override
    public Boolean patientExists(Long id) throws HmsException {
        return patientRepository.existsById(id);
    }

    @Override
    public List<DoctorDropdown> getPatientsById(List<Long> ids) throws HmsException {
        return patientRepository.findAllPatientDropdownsByIds(ids);
    }

    @Override
    public List<PatientDTO> getAllPatients() throws HmsException {
        return ((List<Patient>) patientRepository.findAll())
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }
}
