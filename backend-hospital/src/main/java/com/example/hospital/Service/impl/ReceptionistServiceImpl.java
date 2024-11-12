package com.example.hospital.Service.impl;

import com.example.hospital.Entity.AppointmentsEntity;
import com.example.hospital.Entity.DoctorEntity;
import com.example.hospital.Entity.ReceptionistEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Exception.CustomException.AccountExists;
import com.example.hospital.Model.dto.ReceptionistDTO;
import com.example.hospital.Model.request.BrowseAppoinmentRequest;
import com.example.hospital.Repository.AppointmentsRepository;
import com.example.hospital.Repository.DoctorRepository;
import com.example.hospital.Repository.ReceptionistRepository;
import com.example.hospital.Repository.UserRepository;
import com.example.hospital.Service.ReceptionistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void BrowseAppointment(BrowseAppoinmentRequest browseAppoinmentRequest) {
        AppointmentsEntity appointmentsEntity = appointmentsRepository.findById(
                browseAppoinmentRequest.getAppoinmentId()).get();
        appointmentsEntity.setAppoinmentDate(browseAppoinmentRequest.getAppointmentDate());
        DoctorEntity doctorEntity = doctorRepository.findById(browseAppoinmentRequest.getDoctorId()).get();
        appointmentsEntity.setDoctors(doctorEntity);
        appointmentsEntity.setStatus(browseAppoinmentRequest.getStatus());
        appointmentsRepository.save(appointmentsEntity);
    }

    @Override
    public void AddReceptionist(ReceptionistDTO receptionistDTO) {
        if (userRepository.existsByPhoneOrEmail(receptionistDTO.getPhone(), receptionistDTO.getEmail())) {
            throw new AccountExists("phone or email already exists");
        }
        UserEntity userEntity = modelMapper.map(receptionistDTO, UserEntity.class);
        ReceptionistEntity receptionistEntity = modelMapper.map(receptionistDTO,ReceptionistEntity.class);
        String pass = passwordEncoder.encode(receptionistDTO.getPassword());
        userEntity.setPassword(pass);
        userEntity.setRoles("RECEPTIONIST PATIENT");
        userEntity.setListReceptionist(List.of(receptionistEntity));
        receptionistEntity.setUsers(userEntity);
        userRepository.save(userEntity);
    }
}
