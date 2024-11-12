package com.example.hospital.Service.impl;

import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Model.dto.MajorDTO;
import com.example.hospital.Repository.MajorRepository;
import com.example.hospital.Service.MajorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MajorDTO> findAllMajors() {
        List<MajorEntity> list = majorRepository.findByStatus(1);
        List<MajorDTO> listDTO = new ArrayList<>();
        for (MajorEntity majorEntity : list) {
            MajorDTO majorDTO = modelMapper.map(majorEntity, MajorDTO.class);
            listDTO.add(majorDTO);
        }
        return listDTO;
    }

    @Override
    public void addMajor(MajorDTO majorDTO) {
        MajorEntity majorEntity = modelMapper.map(majorDTO, MajorEntity.class);
        majorEntity.setStatus(1);
        majorRepository.save(majorEntity);
    }
}
