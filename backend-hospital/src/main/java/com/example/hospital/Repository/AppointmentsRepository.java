package com.example.hospital.Repository;

import com.example.hospital.Entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepository  extends JpaRepository<AppointmentsEntity,Long> {
    List<AppointmentsEntity> findByStatus(Integer status);
}
