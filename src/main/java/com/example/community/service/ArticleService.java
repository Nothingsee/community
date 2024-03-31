package com.example.community.service;

import com.example.community.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findArticleList();
}
