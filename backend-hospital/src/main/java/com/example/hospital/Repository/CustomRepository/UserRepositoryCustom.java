package com.example.hospital.Repository.CustomRepository;

import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.request.DoctorRequest;
import com.example.hospital.Model.request.PatientRequest;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findPatientByRequest(PatientRequest patientRequest);
    List<UserEntity> findDoctorByRequest(DoctorRequest doctorRequest);
}
