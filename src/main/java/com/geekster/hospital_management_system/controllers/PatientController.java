package com.geekster.hospital_management_system.controllers;

import com.geekster.hospital_management_system.models.Doctor;
import com.geekster.hospital_management_system.models.Patient;
import com.geekster.hospital_management_system.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity<String> addPatient( @RequestBody Patient patient) {
        patientService.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient added successfully");
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<Doctor>> suggestDoctors(@PathVariable Long patientId) {
        return patientService.suggestDoctor(patientId);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> removePatient(@PathVariable Long patientId) {
        return patientService.removePatientById(patientId);
    }




}
