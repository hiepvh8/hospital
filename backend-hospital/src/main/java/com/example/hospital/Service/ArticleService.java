package com.example.hospital.Service;


import com.example.hospital.Model.dto.ArticleDTO;
import com.example.hospital.Model.request.ArticleRequest;
import com.example.hospital.Model.response.ArticleResponse;

import java.util.List;

public interface ArticleService {
    List<ArticleResponse> findByRequest(ArticleRequest request);
    void AddArticle(ArticleDTO articleDTO);

}
