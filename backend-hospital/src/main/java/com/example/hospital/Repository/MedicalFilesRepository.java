package com.example.hospital.Repository;

import com.example.hospital.Entity.MedicalFilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFilesRepository extends JpaRepository<MedicalFilesEntity,Integer> {
}
