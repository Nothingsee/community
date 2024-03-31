package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long userId;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
