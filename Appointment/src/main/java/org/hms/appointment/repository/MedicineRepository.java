package org.hms.appointment.repository;

import org.hms.appointment.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findAllByPrescription_Id(Long prescriptionId);

    List<Medicine> findAllByPrescription_IdIn(List<Long> prescriptionIds);
}
