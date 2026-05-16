package org.hms.appointment.clients;

import org.hms.appointment.config.FeignClientInterceptor;
import org.hms.appointment.dto.DoctorDTO;
import org.hms.appointment.dto.DoctorName;
import org.hms.appointment.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ProfileMS", url = "${profilems.url}", configuration = FeignClientInterceptor.class)
public interface ProfileClient {

    @GetMapping("/profile/doctor/exists/{id}")
    Boolean doctorExists(@PathVariable("id") Long id);

    @GetMapping("/profile/patient/exists/{id}")
    Boolean patientExists(@PathVariable("id") Long id);

    @GetMapping("/profile/patient/get/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);

    @GetMapping("/profile/doctor/get/{id}")
    DoctorDTO getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/profile/doctor/getDoctorsById")
    List<DoctorName> getDoctorsById(@RequestParam List<Long> ids);

    @GetMapping("/profile/patient/getPatientsById")
    List<DoctorName> getPatientsById(@RequestParam List<Long> ids);
}
