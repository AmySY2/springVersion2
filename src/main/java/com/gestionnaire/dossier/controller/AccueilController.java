package com.gestionnaire.dossier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccueilController {

    @GetMapping("/accueil")
    public String hello(){
        return "Serveur SPRING OK" ;
    }
}