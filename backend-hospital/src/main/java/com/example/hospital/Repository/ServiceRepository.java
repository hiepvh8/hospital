package com.example.hospital.Repository;

import com.example.hospital.Entity.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository  extends JpaRepository<ServicesEntity,Long> {

}
