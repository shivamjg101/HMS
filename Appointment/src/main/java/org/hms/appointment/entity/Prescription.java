package org.hms.appointment.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hms.appointment.dto.PrescriptionDTO;
import org.hms.appointment.dto.PrescriptionDetails;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private LocalDate prescriptionDate;
    private String notes;

    public Prescription(Long id) {
        this.id = id;
    }

    public PrescriptionDTO toDTO() {
        return new PrescriptionDTO(id, patientId, doctorId, appointment.getId(), prescriptionDate, notes, null);
    }

    public PrescriptionDetails toDetails() {
        return new PrescriptionDetails(id, patientId, doctorId, null, null, appointment.getId(), prescriptionDate,
                notes,
                null);
    }
}
