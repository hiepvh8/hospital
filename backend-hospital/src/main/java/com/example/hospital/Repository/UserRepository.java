package com.example.hospital.Repository;

import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Repository.CustomRepository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    UserEntity findByUsername(String username);
    boolean existsByPhoneOrEmail(String phone , String email);
}
