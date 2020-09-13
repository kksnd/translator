package com.example.demo.translator.controller;

import com.example.demo.translator.model.HelloResponseModel;
import com.example.demo.translator.model.TranslateRequestModel;
import com.example.demo.translator.model.TranslatedJsonResponseModel;
import com.example.demo.translator.model.TranslatedResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class TranslationController {
    @GetMapping(path = "hc", produces = "text/html")
    @ResponseBody
    public String healthCheck() {
        RestTemplate restTemplate = new RestTemplate();

        String URL = "http://localhost:5000/healthcheck/json";
        HelloResponseModel helloResponseModel =
                restTemplate.getForObject(URL, HelloResponseModel.class);
        String response = helloResponseModel.getHello();
        return response;
    }


    @GetMapping(path = "translation", produces = "text/html")
    @ResponseBody
    public String translation(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        /*
        // JSON GET
        String URL = "http://localhost:5000/mock/translate/json";
        TranslatedJsonResponseModel translatedResponseModel =
                restTemplate.getForObject(URL, TranslatedJsonResponseModel.class);
        String translatedText = translatedResponseModel.getResponse().getText();
        */

        /*
        // XML GET
        String URL = "http://localhost:5000/mock/translate/xml";
        TranslatedResponseModel translatedResponseModel =
                restTemplate.getForObject(URL, TranslatedResponseModel.class);
        String translatedText = translatedResponseModel.getText();
        */

        // XML POST
        String URL = "http://localhost:5000/translate/to_ja";
        TranslateRequestModel request = new TranslateRequestModel();
        request.setText("How are you?");
        TranslatedResponseModel translatedResponseModel = restTemplate.postForObject(URL, request, TranslatedResponseModel.class);
        String translatedText = translatedResponseModel.getText();

        System.out.println(translatedText);
        return translatedText;
    }
}
