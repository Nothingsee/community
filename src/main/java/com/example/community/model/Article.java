package com.example.community.model;

import lombok.Data;

@Data
public class Article {
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
}
