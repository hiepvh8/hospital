package com.example.hospital.Repository;

import com.example.hospital.Entity.ArticleEntity;
import com.example.hospital.Repository.CustomRepository.ArticleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity,Integer>, ArticleRepositoryCustom {
    List<ArticleEntity> findByTitleContainingOrContentContaining(String keyW, String key);

}
