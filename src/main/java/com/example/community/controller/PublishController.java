package com.example.community.controller;

import com.example.community.mapper.ArticleMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Article;
import com.example.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ArticleMapper articleMapper;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("githubUserToken".equals(cookie.getName())) {
                    User user = userMapper.findUserByToken(cookie.getValue());
                    request.getSession().setAttribute("user", user);
                }
            }
        }
        return "publish";
    }

    @PostMapping("/publish/article")
    public String publishArticle(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("tag") String tag,
                                 HttpServletRequest request, Model model) {
        model.addAttribute("tag", tag);
        model.addAttribute("description", description);
        model.addAttribute("title", title);

        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setTag(tag);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "publish";
        }
        article.setUserId(user.getId());
        article.setGmtCreate(System.currentTimeMillis());
        article.setGmtModified(article.getGmtCreate());
        articleMapper.insert(article);
        return "redirect:/";
    }
}
