package com.example.hospital.Repository;

import com.example.hospital.Entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity,Long> {
}
