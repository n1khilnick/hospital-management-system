package com.geekster.hospital_management_system.repositories;

import com.geekster.hospital_management_system.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDoctorDao extends JpaRepository<Doctor,Long> {
        Doctor findByDoctorId(Long doctorId);
        List<Doctor> findByDoctorCityAndSpeciality(String doctorCity, String speciality);
}
