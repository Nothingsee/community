package com.example.community.mapper;

import com.example.community.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into sys_article(title,description,gmt_create,gmt_modified,user_id,tag) " +
            "values (#{title},#{description},#{gmtCreate},#{gmtModified},#{userId},#{tag})")
    void insert(Article article);

    @Select("select * from sys_article limit #{offset},#{pageSize}")
    List<Article> list(@Param("offset") int offset, @Param(("pageSize")) int pageSize);

    @Select("select * from sys_article order by gmt_modified desc limit #{count}")
    List<Article> topArticle(@Param("count") int count);

    @Select("select count(1) from sys_article")
    int count();
}
