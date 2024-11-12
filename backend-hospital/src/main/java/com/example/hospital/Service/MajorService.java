package com.example.hospital.Service;

import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Model.dto.MajorDTO;


import java.util.List;

public interface MajorService {
    List<MajorDTO> findAllMajors();
    void addMajor(MajorDTO majorDTO);

}
