package com.example.hospital.Repository;

import com.example.hospital.Entity.MajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<MajorEntity,Long> {
    MajorEntity findByName(String name);
    List<MajorEntity> findByStatus(Integer status);
}
