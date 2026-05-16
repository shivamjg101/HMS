package org.hms.profilems.service;

import org.hms.profilems.dto.DoctorDTO;
import org.hms.profilems.dto.DoctorDropdown;
import org.hms.profilems.entity.Doctor;
import org.hms.profilems.exception.HmsException;
import org.hms.profilems.mapper.DoctorMapper;
import org.hms.profilems.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {


    private  final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Long addDoctor(DoctorDTO doctorDTO) throws HmsException {
        if (doctorDTO.getEmail() != null && doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent()) {
            throw new HmsException("DOCTOR_ALREADY_EXISTS");
        }

        if (doctorDTO.getLicenseNo() != null && doctorRepository.findByLicenseNo(doctorDTO.getLicenseNo()).isPresent()) {
            throw new HmsException("DOCTOR_ALREADY_EXISTS");
        }

        Doctor savedDoctor = doctorRepository.save(DoctorMapper.toEntity(doctorDTO));
        return savedDoctor.getId();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) throws HmsException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new HmsException("DOCTOR_NOT_FOUND"));

        return DoctorMapper.toDTO(doctor);
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) throws HmsException {
        doctorRepository.findById(doctorDTO.getId())
                .orElseThrow(() -> new HmsException("DOCTOR_NOT_FOUND"));

        Doctor updatedDoctor = doctorRepository.save(DoctorMapper.toEntity(doctorDTO));
        return DoctorMapper.toDTO(updatedDoctor);
    }

    @Override
    public Boolean doctorExists(Long id) throws HmsException {
        return doctorRepository.existsById(id);
    }

    @Override
    public List<DoctorDropdown> getDoctorDropdowns() throws HmsException {
        return doctorRepository.findAllDoctorDropdowns();
    }

    public List<DoctorDropdown> getDoctorsById(List<Long> ids) throws HmsException {
        return doctorRepository.findAllDoctorDropdownsByIds(ids);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() throws HmsException {
        return ((List<Doctor>) doctorRepository.findAll())
                .stream()
                .map(DoctorMapper::toDTO)
                .toList();
    }
}
