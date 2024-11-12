package com.example.hospital.Repository;

import com.example.hospital.Entity.ReceptionistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionistRepository extends JpaRepository<ReceptionistEntity, Long> {
}
