package org.hms.profilems.repository;

import org.hms.profilems.dto.DoctorDropdown;
import org.hms.profilems.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByAadharNo(String aadharNo);

    @Query("SELECT d.id AS id, d.Name AS name FROM Patient d WHERE d.id in ?1")
    List<DoctorDropdown> findAllPatientDropdownsByIds(List<Long> ids);
}
