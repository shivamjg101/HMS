package org.hms.appointment.repository;

import java.util.List;
import java.util.Optional;

import org.hms.appointment.entity.ApRecord;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ApRecordRepository extends JpaRepository<ApRecord, Long> {
    Optional<ApRecord> findByAppointment_Id(Long appointmentId);

    List<ApRecord> findByPatientId(Long patientId);

    Boolean existsByAppointment_Id(Long appointmentId);
}
