package org.hms.appointment.repository;


import org.hms.appointment.dto.AppointmentDetails;
import org.hms.appointment.dto.MonthlyVisitDTO;
import org.hms.appointment.dto.ReasonCountDTO;
import org.hms.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT new org.hms.appointment.dto.AppointmentDetails(a.id, a.patientId, null, null, null, a.doctorId, null, a.appointmentTime, a.status, a.reason, a.notes) FROM Appointment a  WHERE a.patientId = ?1")
    List<AppointmentDetails> findAllByPatientId(Long patientId);

    @Query("SELECT new org.hms.appointment.dto.AppointmentDetails(a.id, a.patientId, null, null, null, a.doctorId, null, a.appointmentTime, a.status, a.reason, a.notes) FROM Appointment a  WHERE a.doctorId = ?1")
    List<AppointmentDetails> findAllByDoctorId(Long doctorId);

    @Query("SELECT new org.hms.appointment.dto.MonthlyVisitDTO( CAST(FUNCTION('MONTHNAME', a.appointmentTime) as String) , COUNT(a)) FROM Appointment a WHERE a.patientId = ?1 AND YEAR(a.appointmentTime) = YEAR(CURRENT_DATE) GROUP BY FUNCTION('MONTH', a.appointmentTime), CAST(FUNCTION('MONTHNAME', a.appointmentTime) as String) ORDER BY FUNCTION('MONTH', a.appointmentTime)")
    List<MonthlyVisitDTO> countCurrentYearVisitsByPatient(Long patientId);

    @Query("SELECT new org.hms.appointment.dto.MonthlyVisitDTO( CAST(FUNCTION('MONTHNAME', a.appointmentTime) as String) , COUNT(a)) FROM Appointment a WHERE a.doctorId = ?1 AND YEAR(a.appointmentTime) = YEAR(CURRENT_DATE) GROUP BY FUNCTION('MONTH', a.appointmentTime), CAST(FUNCTION('MONTHNAME', a.appointmentTime) as String) ORDER BY FUNCTION('MONTH', a.appointmentTime)")
    List<MonthlyVisitDTO> countCurrentYearVisitsByDoctor(Long doctorId);

    @Query("SELECT new org.hms.appointment.dto.MonthlyVisitDTO( CAST(FUNCTION('MONTHNAME', a.appointmentTime) as String) , COUNT(a)) FROM Appointment a WHERE  YEAR(a.appointmentTime) = YEAR(CURRENT_DATE) GROUP BY FUNCTION('MONTH', a.appointmentTime), CAST(FUNCTION('MONTHNAME', a.appointmentTime) as String) ORDER BY FUNCTION('MONTH', a.appointmentTime)")
    List<MonthlyVisitDTO> countCurrentYearVisits();

    @Query("SELECT new org.hms.appointment.dto.ReasonCountDTO(a.reason, COUNT(a)) FROM Appointment a WHERE a.patientId = ?1 GROUP BY a.reason")
    List<ReasonCountDTO> countReasonsByPatientId(Long patientId);

    @Query("SELECT new org.hms.appointment.dto.ReasonCountDTO(a.reason, COUNT(a)) FROM Appointment a WHERE a.doctorId = ?1 GROUP BY a.reason")
    List<ReasonCountDTO> countReasonsByDoctorId(Long doctorId);

    @Query("SELECT new org.hms.appointment.dto.ReasonCountDTO(a.reason, COUNT(a)) FROM Appointment a  GROUP BY a.reason")
    List<ReasonCountDTO> countReasons();

    List<Appointment> findByAppointmentTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
