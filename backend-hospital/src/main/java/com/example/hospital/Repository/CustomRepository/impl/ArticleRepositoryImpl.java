package com.example.hospital.Repository.CustomRepository.impl;

import com.example.hospital.Entity.ArticleEntity;
import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.request.ArticleRequest;
import com.example.hospital.Repository.ArticleRepository;
import com.example.hospital.Repository.CustomRepository.ArticleRepositoryCustom;
import com.example.hospital.Repository.MajorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    @Autowired
    private MajorRepository majorRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ArticleEntity> findByRequest(ArticleRequest request) {
        List<ArticleEntity> list = new ArrayList<>();
        if (request.getMajor() != null){
            MajorEntity majorEntity = majorRepository.findById(request.getMajor()).get();
            List<ArticleEntity> list1 = majorEntity.getListArticle();
            for (ArticleEntity articleEntity : list1) {
                if(articleEntity.getContent().contains(request.getKeyWord())
                || articleEntity.getTitle().contains(request.getKeyWord())){
                    list.add(articleEntity);
                }
            }
        }else {
            StringBuilder sql = new StringBuilder("SELECT b.* FROM ARTICLE b" +
                    "WHERE b.title LIKE '% " + request.getKeyWord() +
                    "%' OR b.content LIKE '% " + request.getKeyWord() + "%' ");
            Query quey = entityManager.createNativeQuery(sql.toString(), ArticleEntity.class);
            return quey.getResultList();
        }
        return list;
    }
}
