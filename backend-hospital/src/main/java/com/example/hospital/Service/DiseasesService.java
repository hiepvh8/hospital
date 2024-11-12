package com.example.hospital.Service;

import com.example.hospital.Entity.DiseasesEntity;
import com.example.hospital.Model.dto.DiseasesDTO;

import java.util.List;

public interface DiseasesService {
    List<DiseasesDTO> findDiseasesByChar(String s);
    void AddDiseases (DiseasesDTO diseasesDTO);

}
