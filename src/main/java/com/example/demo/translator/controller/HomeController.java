package com.example.demo.translator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(path = "home", produces = "text/html")
    public String hello() {
        return "home";
    }
}