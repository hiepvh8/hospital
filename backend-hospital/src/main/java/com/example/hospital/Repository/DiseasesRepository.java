package com.example.hospital.Repository;

import com.example.hospital.Entity.DiseasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseasesRepository extends JpaRepository<DiseasesEntity,Long> {
    List<DiseasesEntity> findByNameStartingWith( String name);
    List<DiseasesEntity> findByStatusAndNameStartingWith(Integer status,String s);
}
