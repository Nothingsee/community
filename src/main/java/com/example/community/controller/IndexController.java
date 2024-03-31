package com.example.community.controller;

import com.example.community.dto.ArticleDto;
import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import com.example.community.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测试Controlelr
 */
@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ArticleService articleService;

    @GetMapping("/")
    public String Hello(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("githubUserToken".equals(cookie.getName())) {
                    User user = userMapper.findUserByToken(cookie.getValue());
                    request.getSession().setAttribute("user", user);
                }
            }
        }
        List<ArticleDto> articleDtoList =  articleService.findArticleList();
        model.addAttribute("articles", articleDtoList);
        return "index";
    }
}
