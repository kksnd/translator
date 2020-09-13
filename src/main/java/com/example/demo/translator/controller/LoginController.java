package com.example.demo.translator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @GetMapping(path = "hello", produces = "text/html")
    public String hello(Model model) {
        model.addAttribute("message", "This message is from controller");
        return "hello";
    }

    @GetMapping(path = "success", produces = "text/html")
    @ResponseBody
    public String success() {
        return "成功!!!";
    }

    @GetMapping(path = "page1", produces = "text/html")
    @ResponseBody
    public String page1() {
        return "ページ１";
    }
}
