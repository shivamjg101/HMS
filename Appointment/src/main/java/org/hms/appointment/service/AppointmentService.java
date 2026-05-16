package org.hms.appointment.service;

import org.hms.appointment.dto.AppointmentDTO;
import org.hms.appointment.dto.AppointmentDetails;
import org.hms.appointment.dto.MonthlyVisitDTO;
import org.hms.appointment.dto.ReasonCountDTO;
import org.hms.appointment.exception.HmsException;

import java.util.List;



public interface AppointmentService {
    Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException;

    void cancelAppointment(Long appointmentId) throws HmsException;

    void completeAppointment(Long appointmentId);

    void rescheduleAppointment(Long appointmentId, String newDateTime);

    AppointmentDTO getAppointmentDetails(Long appointmentId) throws HmsException;

    AppointmentDetails getAppointmentDetailsWithName(Long appointmentId) throws HmsException;

    List<AppointmentDetails> getAllAppointmentsByPatientId(Long patientId) throws HmsException;

    List<AppointmentDetails> getAllAppointmentsByDoctorId(Long doctorId) throws HmsException;

    List<MonthlyVisitDTO> getAppointmentCountByPatient(Long patientId) throws HmsException;

    List<MonthlyVisitDTO> getAppointmentCountByDoctor(Long doctorId) throws HmsException;

    List<MonthlyVisitDTO> getAppointmentCounts() throws HmsException;

    List<ReasonCountDTO> getReasonCountByPatient(Long patientId);

    List<ReasonCountDTO> getReasonCountByDoctor(Long doctorId);

    List<ReasonCountDTO> getReasonCount();

    List<AppointmentDetails> getTodaysAppointments();

}
