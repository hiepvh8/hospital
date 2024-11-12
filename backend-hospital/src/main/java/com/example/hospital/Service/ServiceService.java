package com.example.hospital.Service;


import com.example.hospital.Entity.ServicesEntity;
import com.example.hospital.Model.dto.ServiceDTO;
import com.example.hospital.Model.response.ServiceResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceResponse> findServiceByMajor(Long majorId);
    void addService (ServiceDTO serviceDTO);

}
