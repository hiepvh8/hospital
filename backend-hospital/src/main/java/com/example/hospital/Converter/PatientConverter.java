package com.example.hospital.Converter;

import com.example.hospital.Entity.MedicalFilesEntity;
import com.example.hospital.Entity.PatientEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.response.PatientResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PatientResponse ConverterFromUserEntity (UserEntity userEntity) {
        PatientResponse patientResponse = modelMapper.map(userEntity, PatientResponse.class);
        PatientEntity patientEntity = userEntity.getListPatient().get(0);
        patientResponse.setAddress(patientEntity.getAddress());
        List<MedicalFilesEntity> medicalFilesEntity = patientEntity.getListMedicalFiles();
        List<String> listPatientIllness = medicalFilesEntity.stream()
                .map(MedicalFilesEntity::getPatientIllness).collect(Collectors.toList());
        patientResponse.setListPatientIllness(listPatientIllness);
        return patientResponse;
    }
}
