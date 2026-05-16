package org.hms.profilems.controller;

import org.hms.profilems.dto.DoctorDropdown;
import org.hms.profilems.dto.PatientDTO;
import org.hms.profilems.exception.HmsException;
import org.hms.profilems.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/profile/patient")
@Validated
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addPatient(@RequestBody PatientDTO patientDTO) throws HmsException {
        return new ResponseEntity<>(patientService.addPatient(patientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) throws HmsException {
        return new ResponseEntity<>(patientService.updatePatient(patientDTO), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> patientExists(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.patientExists(id), HttpStatus.OK);
    }

    @GetMapping("/getProfileId/{id}")
    public ResponseEntity<Long> getProfileId(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.getPatientById(id).getProfilePictureId(), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDTO>> getAllPatients() throws HmsException {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/getPatientsById")
    public ResponseEntity<List<DoctorDropdown>> getPatientsById(@RequestParam List<Long> ids) throws HmsException {
        return new ResponseEntity<>(patientService.getPatientsById(ids), HttpStatus.OK);
    }
}
