package org.hms.appointment.service;

import org.hms.appointment.dto.MedicineDTO;
import org.hms.appointment.dto.PrescriptionDTO;
import org.hms.appointment.dto.PrescriptionDetails;
import org.hms.appointment.exception.HmsException;

import java.util.List;



public interface PrescriptionService {
    public Long savePrescription(PrescriptionDTO request);

    public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws HmsException;

    public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws HmsException;

    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HmsException;

    public List<PrescriptionDetails> getPrescriptions() throws HmsException;

    List<MedicineDTO> getMedicineByPatientId(Long patientId) throws HmsException;

}
