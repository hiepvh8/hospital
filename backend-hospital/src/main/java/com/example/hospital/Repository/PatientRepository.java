package com.example.hospital.Repository;

import com.example.hospital.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity,Integer> {

}
