package com.example.demo.translator.controller;

import com.example.demo.translator.model.*;
import com.example.demo.translator.repository.PairTextsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TranslationController {

    @Autowired
    private PairTextsRepository pairTextsRepository;

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

        List<PairTextsModel> histories = new ArrayList<>();
        int i = 0;
        for (PairTextsModel pairTexts : pairTextsRepository.findAll()) {
            histories.add(pairTexts);
            if (++i >= 10) {
                break;
            }
        }
        model.addAttribute("histories", histories);

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

        // insert result
        PairTextsModel newPairTexts = new PairTextsModel();
        newPairTexts.setSource(source);
        newPairTexts.setTarget(translatedText);
        if (source.length() > 5) {
            newPairTexts.setSourceHead(source.substring(0, 5) + "...");
        } else {
            newPairTexts.setSourceHead(source);
        }
        if (translatedText.length() > 5) {
            newPairTexts.setTargetHead(translatedText.substring(0, 5) + "...");
        } else {
            newPairTexts.setTargetHead(translatedText);
        }
        pairTextsRepository.save(newPairTexts);

        // for view
        model.addAttribute("source", source);
        model.addAttribute("target", translatedText);

        List<PairTextsModel> histories = new ArrayList<>();
        int i = 0;
        for (PairTextsModel pairTexts : pairTextsRepository.findAll()) {
            histories.add(pairTexts);
            if (++i >= 10) {
                break;
            }
        }
        model.addAttribute("histories", histories);

        return "translation";
    }
}
