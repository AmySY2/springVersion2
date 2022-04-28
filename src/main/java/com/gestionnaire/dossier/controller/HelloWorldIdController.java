package com.gestionnaire.dossier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldIdController {

    @GetMapping("/hello")
    public String direBonjour() {
        return "hello web service";
    }
}
