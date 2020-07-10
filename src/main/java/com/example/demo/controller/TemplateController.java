package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("dashboard")
    public String getDashboard() {
        return "dashboard";
    }
}
