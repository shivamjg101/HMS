package org.hms.profilems.repository;

import org.hms.profilems.dto.DoctorDropdown;
import org.hms.profilems.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findByLicenseNo(String licenseNo);

    @Query("SELECT d.id AS id, d.Name AS name FROM Doctor d")
    List<DoctorDropdown> findAllDoctorDropdowns();

    @Query("SELECT d.id AS id, d.Name AS name FROM Doctor d WHERE d.id in ?1")
    List<DoctorDropdown> findAllDoctorDropdownsByIds(List<Long> ids);
}
