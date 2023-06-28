package com.geekster.hospital_management_system.services;

import com.geekster.hospital_management_system.models.Doctor;
import com.geekster.hospital_management_system.repositories.IDoctorDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctorDao doctorDao;

    public ResponseEntity<String> addDoctor(Doctor doctor) {
       JSONObject valid = validDoctor(doctor);

       if(!valid.isEmpty()){
           return new ResponseEntity<>(valid.toString(), HttpStatus.BAD_REQUEST);
       }
        doctorDao.save(doctor);
       return new ResponseEntity<>("Doctor added successfully !!",HttpStatus.CREATED);
    }

    public JSONObject validDoctor(Doctor doctor) {
        JSONObject errors = new JSONObject();

        String city = doctor.getDoctorCity();

        if(!city.equalsIgnoreCase("delhi") && !city.equalsIgnoreCase("noida")&& !city.equalsIgnoreCase("faridabad")){
            errors.put(city,"Doctor should live in delhi, noida and faridabad only.");
        }

        String speciality = doctor.getSpeciality();

        if(!speciality.equalsIgnoreCase("Orthopedic") && !speciality.equalsIgnoreCase("Gynecology")
                && !speciality.equalsIgnoreCase("Dermatology") && !speciality.equalsIgnoreCase("ENT")){
            errors.put(speciality,"Doctors should be specialized in Orthopedic, Gynecology, Dermatology and ENT only.");
        }
        return errors;
    }

    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }


    public ResponseEntity<String> removeDoctorById(Long doctorId) {

        if(doctorDao.findById(doctorId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Doctor with id "+doctorId+" is not available.");
        }

        doctorDao.deleteById(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor with id "+doctorId+" is successfully deleted.");

    }


}
