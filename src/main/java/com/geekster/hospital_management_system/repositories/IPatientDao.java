package com.geekster.hospital_management_system.repositories;

import com.geekster.hospital_management_system.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientDao extends JpaRepository<Patient,Long> {
    Patient findFirstByPatientEmail(String userEmail);
}
