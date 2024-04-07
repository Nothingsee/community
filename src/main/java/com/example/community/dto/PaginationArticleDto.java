package com.example.community.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationArticleDto {
    private List<ArticleDto> articles;
    private boolean showPrevious = true;
    private boolean showFistPage = false;
    private boolean showLastPage = false;
    private boolean showNext = true;
    private Integer currentPage;
    private Integer pageCount;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer pageCount, Integer currentPage, Integer pageSize) {
        //当前页
        if (currentPage < 1) {
            this.currentPage = 1;
        } else if (currentPage > pageCount) {
            this.currentPage = pageCount;
        } else {
            this.currentPage = currentPage;
        }
        //上一页
        if (currentPage == 1) {
            this.showPrevious = false;
        }
        //下一页
        if (currentPage == pageCount) {
            this.showNext = false;
        }
        //页码
        pages.add(this.currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }
            if (currentPage + i <= pageCount) {
                pages.add(currentPage + i);
            }
        }
        //首页
        if (!CollectionUtils.isEmpty(pages) && !pages.contains(1)) {
            this.showFistPage = true;
        }
        //尾页
        if (!CollectionUtils.isEmpty(pages) && !pages.contains(pageCount)) {
            this.showLastPage = true;
        }
        this.pageCount = pageCount;
    }
}
