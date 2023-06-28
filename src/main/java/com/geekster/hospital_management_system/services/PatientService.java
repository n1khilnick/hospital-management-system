package com.geekster.hospital_management_system.services;


import com.geekster.hospital_management_system.models.Doctor;
import com.geekster.hospital_management_system.repositories.IDoctorDao;
import com.geekster.hospital_management_system.repositories.IPatientDao;
import com.geekster.hospital_management_system.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientDao patientDao;

    @Autowired
    DoctorService doctorService;

    @Autowired
    IDoctorDao doctorDao;

    public void addPatient(Patient patient) {
        patientDao.save(patient);
    }


    public ResponseEntity<List<Doctor>> suggestDoctor(@PathVariable Long patientId){
        Patient patient = patientDao.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));

        String status = "";
        String city = patient.getPatientCity();
        String symptom = patient.getSymptoms();

        List<Doctor> doctors = doctorDao.findByDoctorCityAndSpeciality(city, getSpeciality(symptom));

        if (doctors.isEmpty()) {
            // Edge-Case 1: If there isn't any doctor on that location (i.e. outside Delhi, Noida, Faridabad)
            if (!city.equals("Delhi") && !city.equals("Noida") && !city.equals("Faridabad")) {
                status = "We are still waiting to expand to your location";
            }
            throw new IllegalStateException(status);
        }

        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    public String getSpeciality(String symptom) {
        return switch (symptom) {
            case "Arthritis", "Back Pain", "Tissue injuries" -> "Orthopedic";
            case "Dysmenorrhea" -> "Gynecology";
            case "Skin infection", "Skin burn" -> "Dermatology";
            case "Ear pain" -> "ENT";
            default -> throw new IllegalArgumentException("There isn’t any doctor present at your location for your symptom");
        };

    }

    public ResponseEntity<String> removePatientById(Long patientId) {
        if(patientDao.findById(patientId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Patient with id "+patientId+" is not available.");
        }

        patientDao.deleteById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body("Patient with id "+patientId+" is successfully deleted.");
    }
}
