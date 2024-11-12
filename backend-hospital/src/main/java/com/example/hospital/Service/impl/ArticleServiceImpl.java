package com.example.hospital.Service.impl;

import com.example.hospital.Entity.AdminEntity;
import com.example.hospital.Entity.ArticleEntity;
import com.example.hospital.Entity.MajorEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.dto.ArticleDTO;
import com.example.hospital.Model.request.ArticleRequest;
import com.example.hospital.Model.response.ArticleResponse;
import com.example.hospital.Repository.ArticleRepository;
import com.example.hospital.Repository.MajorRepository;
import com.example.hospital.Repository.UserRepository;
import com.example.hospital.Service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ArticleResponse> findByRequest(ArticleRequest request) {
        List<ArticleEntity> list = articleRepository.findByRequest(request);
        List<ArticleResponse> responseList = new ArrayList<>();
        for (ArticleEntity articleEntity : list) {
            responseList.add(modelMapper.map(articleEntity, ArticleResponse.class));
        }
        return responseList;
    }

    @Override
    public void AddArticle(ArticleDTO articleDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(username);
        AdminEntity admin = userEntity.getListAdmin().get(0);
        MajorEntity majorEntity = majorRepository.findById(articleDTO.getMajorID()).get();
        ArticleEntity articleEntity = modelMapper.map(articleDTO, ArticleEntity.class);
        articleEntity.setStatus(1);
        articleEntity.setAdmin(admin);
        articleEntity.setMajors(majorEntity);
        articleRepository.save(articleEntity);


    }
}
