package com.geekster.hospital_management_system.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@NoArgsConstructor
@Table(name="patientTable")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patientId")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @NotBlank
    @Length(min = 3,message = "Please enter name of at-least 3 characters only !!")
    private String patientName;

    @NotBlank
    @Length(max = 20,message = "Please enter max of max 20 characters only !!")
    private String patientCity;

    @NotBlank
    @Email(message = "Please enter a valid email")
    private String patientEmail;

    @NotBlank
    private String patientPassword;

    @NotEmpty
    private String patientContact;


    private String symptoms;

    public Patient(String patientName, String patientCity, String patientEmail, String patientPassword, String patientContact, String symptoms) {
        this.patientName = patientName;
        this.patientCity = patientCity;
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
        this.patientContact = patientContact;
        this.symptoms = symptoms;
    }

//    @OneToOne(mappedBy = "patient")
//    Appointment appointments;
}
