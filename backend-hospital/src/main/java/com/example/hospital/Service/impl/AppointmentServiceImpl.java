package com.example.hospital.Service.impl;

import com.example.hospital.Converter.AppointmentConverter;
import com.example.hospital.Entity.AppointmentsEntity;
import com.example.hospital.Entity.PatientEntity;
import com.example.hospital.Model.response.AppointmentResponse;
import com.example.hospital.Repository.AppointmentsRepository;
import com.example.hospital.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private AppointmentConverter appointmentConverter;


    @Override
    public List<AppointmentResponse> listAppointments() {
        List<AppointmentsEntity> entityList = appointmentsRepository.findByStatus(1);
        List<AppointmentResponse> responseList = new ArrayList<>();
        for (AppointmentsEntity entity : entityList) {
            AppointmentResponse appointmentResponse = appointmentConverter.ConverterFromEntity(entity);
            responseList.add(appointmentResponse);
        }
        return responseList;
    }
}
