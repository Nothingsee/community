package com.example.community.service.impl;

import com.example.community.dto.ArticleDto;
import com.example.community.mapper.ArticleMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Article;
import com.example.community.model.User;
import com.example.community.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleDto> findArticleList() {
        List<Article> articles = articleMapper.topArticle(10);
        if (CollectionUtils.isEmpty(articles)) {
            return new ArrayList<>();
        }
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.forEach(article -> {
            User user = userMapper.findUserByUserId(article.getUserId());
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            articleDto.setUser(user);
            articleDtos.add(articleDto);
        });
        return articleDtos;
    }
}
