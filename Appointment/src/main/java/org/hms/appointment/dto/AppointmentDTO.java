package org.hms.appointment.dto;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hms.appointment.entity.Appointment;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private Status status;
    private String reason;
    private String notes;

    public Appointment toEntity() {
        return new Appointment(id, patientId, doctorId, appointmentTime, status, reason, notes);
    }
}
