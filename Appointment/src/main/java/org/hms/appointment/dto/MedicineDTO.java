package org.hms.appointment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hms.appointment.entity.Medicine;
import org.hms.appointment.entity.Prescription;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Long id;
    private String name;
    private Long medicineId;
    private String dosage;
    private String frequency;
    private Integer duration; // in days
    private String route; // e.g., oral, intravenous
    private String type; // e.g., tablet, syrup, injection
    private String instructions;
    private Long prescriptionId;

    public Medicine toEntity() {
        return new Medicine(id, name, medicineId, dosage, frequency, duration, route, type, instructions,
                new Prescription(prescriptionId));
    }
}
