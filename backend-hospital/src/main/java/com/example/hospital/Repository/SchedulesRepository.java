package com.example.hospital.Repository;

import com.example.hospital.Entity.SchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<SchedulesEntity,Integer> {
}
