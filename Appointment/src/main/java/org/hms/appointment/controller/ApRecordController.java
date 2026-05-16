package org.hms.appointment.controller;

import lombok.RequiredArgsConstructor;
import org.hms.appointment.dto.ApRecordDTO;
import org.hms.appointment.dto.MedicineDTO;
import org.hms.appointment.dto.PrescriptionDetails;
import org.hms.appointment.dto.RecordDetails;
import org.hms.appointment.exception.HmsException;
import org.hms.appointment.service.ApRecordService;
import org.hms.appointment.service.MedicineService;
import org.hms.appointment.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/appointment/report")
@Validated
@RequiredArgsConstructor
public class ApRecordController {
    private final ApRecordService apRecordService;
    private final PrescriptionService prescriptionService;
    private final MedicineService medicineService;

    @PostMapping("/create")
    public ResponseEntity<Long> createAppointmentReport(@RequestBody ApRecordDTO request) throws HmsException {
        return new ResponseEntity<>(apRecordService.createApRecord(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAppointmentReport(@RequestBody ApRecordDTO request) throws HmsException {
        apRecordService.updateApRecord(request);
        return new ResponseEntity<>("Appointment Report Updated.", HttpStatus.OK);

    }

    @GetMapping("/getByAppointmentId/{appointmentId}")
    public ResponseEntity<ApRecordDTO> getAppointmentReportByAppointmentId(@PathVariable Long appointmentId)
            throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordByAppointmentId(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getDetailsByAppointmentId/{appointmentId}")
    public ResponseEntity<ApRecordDTO> getAppointmentReportDetailsByAppointmentId(@PathVariable Long appointmentId)
            throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordDetailsByAppointmentId(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getById/{recordId}")
    public ResponseEntity<ApRecordDTO> getAppointmentReportById(@PathVariable Long recordId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordById(recordId), HttpStatus.OK);
    }

    @GetMapping("/getRecordsByPatientId/{patientId}")
    public ResponseEntity<List<RecordDetails>> getRecordsByPatientId(@PathVariable Long patientId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getRecordsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/isRecordExists/{appointmentId}")
    public ResponseEntity<Boolean> isRecordExists(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.isRecordExists(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getPrescriptionsByPatientId/{patientId}")
    public ResponseEntity<List<PrescriptionDetails>> getPrescriptionsByPatientId(@PathVariable Long patientId)
            throws HmsException {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/getAllPrescriptions")
    public ResponseEntity<List<PrescriptionDetails>> getAllPrescriptions() throws HmsException {
        return new ResponseEntity<>(prescriptionService.getPrescriptions(), HttpStatus.OK);
    }

    @GetMapping("/getMedicinesByPrescriptionId/{prescriptionId}")
    public ResponseEntity<List<MedicineDTO>> getMedicinesByPrescriptionId(@PathVariable Long prescriptionId) {
        return new ResponseEntity<>(medicineService.getAllMedicinesByPrescriptionId(prescriptionId), HttpStatus.OK);
    }
}
