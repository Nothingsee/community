package com.example.community.service.impl;

import com.example.community.dto.ArticleDto;
import com.example.community.dto.PaginationArticleDto;
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
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public PaginationArticleDto findArticleList(int page, int pageSize) {
        if (page < 1) {
            page = 1;
        }
        int articleCount = articleMapper.count();
        if (pageSize > articleCount) {
            pageSize = articleCount;
        }
        int offset = (page -1) * pageSize;
        List<Article> articles = articleMapper.list(offset, pageSize);
        if (CollectionUtils.isEmpty(articles)) {
            return new PaginationArticleDto();
        }
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.forEach(article -> {
            User user = userMapper.findUserByUserId(article.getUserId());
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            articleDto.setUser(user);
            articleDtos.add(articleDto);
        });
        PaginationArticleDto paginationArticleDto = new PaginationArticleDto();
        paginationArticleDto.setArticles(articleDtos);
        //计算页码
        int pageCount = articleCount % pageSize == 0 ? articleCount / pageSize : articleCount / pageSize + 1;
        paginationArticleDto.setPagination(pageCount, page, pageSize);
        return paginationArticleDto;
    }
}
