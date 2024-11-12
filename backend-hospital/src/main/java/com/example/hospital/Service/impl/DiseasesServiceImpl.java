package com.example.hospital.Service.impl;

import com.example.hospital.Entity.DiseasesEntity;
import com.example.hospital.Entity.DiseasesMajor;
import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Exception.CustomException.DataNotFoundException;
import com.example.hospital.Model.dto.DiseasesDTO;
import com.example.hospital.Repository.DiseasesMajorRepository;
import com.example.hospital.Repository.DiseasesRepository;
import com.example.hospital.Repository.MajorRepository;
import com.example.hospital.Service.DiseasesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DiseasesServiceImpl implements DiseasesService {

    @Autowired
    private DiseasesRepository diseasesRepository;

    @Autowired
    private MajorRepository majorRepository;

    /*@Autowired
    private DiseasesMajorRepository diseasesMajorRepository;*/

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DiseasesDTO> findDiseasesByChar(String s) {
        List<DiseasesEntity> entityList = diseasesRepository.findByStatusAndNameStartingWith(1,s);
        List<DiseasesDTO> dtoList = new ArrayList<>();
        for(DiseasesEntity entity : entityList) {
            DiseasesDTO diseasesDTO = modelMapper.map(entity, DiseasesDTO.class);
            dtoList.add(diseasesDTO);
        }
        Collections.sort(dtoList, Comparator.comparing(p -> p.getName()));

        return dtoList;
    }

    @Override
    public void AddDiseases(DiseasesDTO diseasesDTO) {
        MajorEntity majorEntity = majorRepository.findById(diseasesDTO.getMajorId()).orElse(null);
        if(majorEntity == null) {
            throw new DataNotFoundException("Major not found");
        }
        DiseasesEntity diseases = modelMapper.map(diseasesDTO,DiseasesEntity.class);
        diseases.setStatus(1);

        DiseasesMajor diseasesMajor = DiseasesMajor.builder()
                .status(1)
                .diseases(diseases)
                .majors(majorEntity).build();
        diseases.setListDiseasesMajor(List.of(diseasesMajor));
        diseasesRepository.save(diseases);
    }
}
