package com.example.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试Controlelr
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String Hello() {
        return "index";
    }
}
