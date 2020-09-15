package com.example.demo.translator.controller;

import com.example.demo.translator.model.HelloResponseModel;
import com.example.demo.translator.model.TranslateRequestModel;
import com.example.demo.translator.model.TranslatedJsonResponseModel;
import com.example.demo.translator.model.TranslatedResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class TranslationController {
    @GetMapping(path = "hc", produces = "text/html")
    public String healthCheck(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        String URL = "http://localhost:5000/healthcheck/json";
        HelloResponseModel helloResponseModel =
                restTemplate.getForObject(URL, HelloResponseModel.class);
        String response = helloResponseModel.getHello();
        if (response == null) {
            model.addAttribute("message", "null response");
        } else {
            model.addAttribute("message", response);
        }
        return "hello";
    }


    @GetMapping(path = "translation", produces = "text/html")
    public String translationGet(Model model) {
        model.addAttribute("source", "");
        model.addAttribute("target", "");
        return "translation";
    }


    @PostMapping(path = "translation", produces = "text/html")
    public String translation(Model model, @RequestParam("source") String source) {
        RestTemplate restTemplate = new RestTemplate();

        // XML POST
        String URL = "http://localhost:5000/translate/to_en";
        TranslateRequestModel request = new TranslateRequestModel();
        request.setText(source);
        TranslatedResponseModel translatedResponseModel = restTemplate.postForObject(URL, request, TranslatedResponseModel.class);
        String translatedText = translatedResponseModel.getText();

        System.out.println(translatedText);

        model.addAttribute("source", source);
        model.addAttribute("target", translatedText);

        return "translation";
    }
}
