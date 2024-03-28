package com.example.community.controller;

import com.example.community.mapper.UserMapper;
import com.example.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试Controlelr
 */
@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/")
    public String Hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("githubUserToken".equals(cookie.getName())) {
                    User user = userMapper.findUserByToken(cookie.getValue());
                    request.getSession().setAttribute("user", user);
                }
            }
        }
        return "index";
    }
}
