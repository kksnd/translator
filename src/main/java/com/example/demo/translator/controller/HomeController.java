package com.example.demo.translator.controller;

import com.example.demo.translator.model.PairTextsModel;
import com.example.demo.translator.repository.PairTextsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private PairTextsRepository pairTextsRepository;

    @GetMapping(path = "home", produces = "text/html")
    public String hello() {
        return "home";
    }
    @GetMapping(path = "db", produces = "text/html")
    public String dbTest(Model model) {
        // insert example
        PairTextsModel newPairTexts = new PairTextsModel();
        newPairTexts.setSource("Hello, I like sleeping");
        newPairTexts.setTarget("こんにちは、寝るのが好きです");
        newPairTexts.setSourceHead("Hello");
        newPairTexts.setTargetHead("こんにちは");
        pairTextsRepository.save(newPairTexts);

        // show example
        String message = "";
        for (PairTextsModel pairTexts : pairTextsRepository.findAll()) {
            message = message
                    + pairTexts.getSourceHead()
                    + ": " + pairTexts.getSource() + " => "
                    + pairTexts.getTargetHead()
                    + ": " + pairTexts.getTarget();
        }
        model.addAttribute("message", message);
        return "hello";
    }
}