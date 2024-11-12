package com.example.hospital.Service.impl;

import com.example.hospital.Entity.AppointmentsEntity;
import com.example.hospital.Entity.PatientEntity;
import com.example.hospital.Entity.ServicesEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.request.AppointmentRequest;
import com.example.hospital.Model.request.ServiceRequest;
import com.example.hospital.Repository.AppointmentsRepository;
import com.example.hospital.Repository.PatientRepository;
import com.example.hospital.Repository.ServiceRepository;
import com.example.hospital.Repository.UserRepository;
import com.example.hospital.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void MakeAppointment(AppointmentRequest appointmentRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(username);
        PatientEntity patient = userEntity.getListPatient().get(0);
        AppointmentsEntity appointmentsEntity = AppointmentsEntity.builder()
                .note(appointmentRequest.getNote())
                .status(0)
                .dateOfWeek(appointmentRequest.getDate_of_week().stream()
                           .collect(Collectors.joining(" ")))
                .patient(patient)
                .build();
        appointmentsRepository.save(appointmentsEntity);
    }

    @Override
    public void MakeService(ServiceRequest serviceRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(username);
        PatientEntity patient = userEntity.getListPatient().get(0);
        ServicesEntity servicesEntity = serviceRepository.findById(serviceRequest.getId()).get();
        AppointmentsEntity appointmentsEntity = AppointmentsEntity.builder()
                .note("Đăng kí dịch vụ " + servicesEntity.getName())
                .status(0)
                .dateOfWeek(serviceRequest.getDateOfWeek().stream()
                        .collect(Collectors.joining(" ")))
                .patient(patient)
                .build();
        appointmentsRepository.save(appointmentsEntity);

    }
}
