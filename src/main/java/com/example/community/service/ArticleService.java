package com.example.community.service;

import com.example.community.dto.PaginationArticleDto;

import java.util.List;

public interface ArticleService {

    PaginationArticleDto findArticleList(int page, int pageSize);
}
