package com.example.hospital.Repository.CustomRepository;

import com.example.hospital.Entity.ArticleEntity;
import com.example.hospital.Model.request.ArticleRequest;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleEntity> findByRequest(ArticleRequest request);
}
