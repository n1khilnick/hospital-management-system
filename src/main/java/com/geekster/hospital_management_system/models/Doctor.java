package com.geekster.hospital_management_system.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Doctor.class,property = "doctorId")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @NotBlank
    private String doctorName;

    @NotBlank
    @Email(message = "Please enter correct email !!")
    private String doctorEmail;

    @NotBlank
    @Size(max = 20)
    private String doctorCity;

    @NotBlank
//    @Pattern(regexp = "\\d{10,}",message = "Please enter correct phone number")
    @Length(min = 10,max = 10,message = "Please enter correct phone number")
    private String doctorPhoneNumber;

    private String speciality;

//    @OneToMany(mappedBy = "doctor")
//    List<Appointment> appointments;

}
