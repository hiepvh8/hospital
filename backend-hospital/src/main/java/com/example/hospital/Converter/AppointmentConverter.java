package com.example.hospital.Converter;

import com.example.hospital.Entity.AppointmentsEntity;
import com.example.hospital.Entity.PatientEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.response.AppointmentResponse;
import com.example.hospital.Repository.PatientRepository;
import com.example.hospital.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter {



    public AppointmentResponse ConverterFromEntity (AppointmentsEntity appointmentsEntity) {
        PatientEntity patientEntity = appointmentsEntity.getPatient();
        UserEntity userEntity = patientEntity.getUsers();
        AppointmentResponse appointmentResponse = AppointmentResponse.builder()
                .phone(userEntity.getPhone())
                .id(appointmentsEntity.getId())
                .email(userEntity.getEmail())
                .note(appointmentsEntity.getNote())
                .dateOfWeek(appointmentsEntity.getDateOfWeek())
                .fullName(userEntity.getFullname())
                .username(userEntity.getUsername())
                .build();

        return appointmentResponse;
    }
}
