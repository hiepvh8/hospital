package com.example.hospital.Service.impl;

import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Entity.ServicesEntity;
import com.example.hospital.Model.dto.ServiceDTO;
import com.example.hospital.Model.response.ServiceResponse;
import com.example.hospital.Repository.MajorRepository;
import com.example.hospital.Repository.ServiceRepository;
import com.example.hospital.Service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    public List<ServiceResponse> findServiceByMajor(Long majorId) {
        MajorEntity major = majorRepository.findById(majorId).get();
        List<ServicesEntity> entityList = major.getListService();
        List<ServiceResponse> responseList = new ArrayList<>();
        for (ServicesEntity service : entityList) {
            if (service.getStatus() == 1) {
                ServiceResponse response = modelMapper.map(service, ServiceResponse.class);
                responseList.add(response);
            }
        }
        return responseList;
    }

    @Override
    public void addService(ServiceDTO serviceDTO) {
        ServicesEntity servicesEntity = modelMapper.map(serviceDTO, ServicesEntity.class);
        MajorEntity majorEntity = majorRepository.findById(serviceDTO.getMajorId()).get();
        servicesEntity.setMajors(majorEntity);
        serviceRepository.save(servicesEntity);
    }
}
