package com.geekster.hospital_management_system.controllers;

import com.geekster.hospital_management_system.models.Doctor;
import com.geekster.hospital_management_system.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/getAll")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> removeDoctor(@PathVariable Long doctorId) {
        return doctorService.removeDoctorById(doctorId);
    }


}
