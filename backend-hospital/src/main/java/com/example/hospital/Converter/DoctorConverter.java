package com.example.hospital.Converter;

import com.example.hospital.Entity.DoctorEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.response.DoctorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter {
    @Autowired
    private ModelMapper modelMapper;

    public DoctorResponse ConverterFromUserEntity(UserEntity userEntity) {
        DoctorResponse doctorResponse = modelMapper.map(userEntity, DoctorResponse.class);
        DoctorEntity doctorEntity = userEntity.getListDoctor().get(0);
        doctorResponse.setDegree(doctorEntity.getDegree());
        doctorResponse.setPosition(doctorEntity.getPosition());
        doctorResponse.setNumberOfReviews(doctorEntity.getNumberOfReviews());
        doctorResponse.setTotalScore(doctorEntity.getTotalScore());
        doctorResponse.setImages(doctorEntity.getImage());
        return doctorResponse;
    }
}
