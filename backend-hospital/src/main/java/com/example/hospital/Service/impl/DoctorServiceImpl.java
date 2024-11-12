package com.example.hospital.Service.impl;

import com.example.hospital.Converter.DoctorConverter;
import com.example.hospital.Entity.DoctorEntity;
import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Exception.CustomException.AccountExists;
import com.example.hospital.Model.dto.DoctorDTO;
import com.example.hospital.Model.request.DoctorRequest;
import com.example.hospital.Model.response.DoctorResponse;
import com.example.hospital.Repository.DoctorRepository;
import com.example.hospital.Repository.MajorRepository;
import com.example.hospital.Repository.UserRepository;
import com.example.hospital.Service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorConverter doctorConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<DoctorResponse> findByRequest(DoctorRequest request) {
        List<UserEntity> list = userRepository.findDoctorByRequest(request);
        List<DoctorResponse> responses = new ArrayList<>();
        for (UserEntity user : list) {
            DoctorResponse doctorResponse = doctorConverter.ConverterFromUserEntity(user);
            responses.add(doctorResponse);
        }
        return responses;
    }

    @Override
    public void addDoctor(DoctorDTO doctorDTO) {
        if (userRepository.existsByPhoneOrEmail(doctorDTO.getPhone(), doctorDTO.getEmail())) {
            throw new AccountExists("phone or email already exists");
        }
        MajorEntity majorEntity = majorRepository.findById(doctorDTO.getMajorId()).get();
        UserEntity userEntity = modelMapper.map(doctorDTO, UserEntity.class);
        DoctorEntity doctorEntity = modelMapper.map(doctorDTO, DoctorEntity.class);
        String pass = passwordEncoder.encode(doctorDTO.getPassword());
        userEntity.setPassword(pass);
        userEntity.setStatus(1);
        doctorEntity.setTotalScore(0L);
        doctorEntity.setNumberOfReviews(0);
        userEntity.setListDoctor(List.of(doctorEntity));
        doctorEntity.setStatus(1);
        doctorEntity.setUser(userEntity);
        doctorEntity.setMajor(majorEntity);
        userRepository.save(userEntity);
    }
}
