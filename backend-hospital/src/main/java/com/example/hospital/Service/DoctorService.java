package com.example.hospital.Service;


import com.example.hospital.Model.dto.DoctorDTO;
import com.example.hospital.Model.request.DoctorRequest;
import com.example.hospital.Model.response.DoctorResponse;

import java.util.List;

public interface DoctorService {
List<DoctorResponse> findByRequest( DoctorRequest request);
    void addDoctor(DoctorDTO doctorDTO);
}
