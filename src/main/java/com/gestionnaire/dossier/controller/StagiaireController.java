package com.gestionnaire.dossier.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.dao.StagiaireDao;
import com.gestionnaire.dossier.model.Formation;
import com.gestionnaire.dossier.model.Stagiaire;
import com.gestionnaire.dossier.vew.VueStagiaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StagiaireController {

    private StagiaireDao stagiaireDao;

    @Autowired
    public StagiaireController(StagiaireDao stagiaireDao) {
        this.stagiaireDao = stagiaireDao;
    }

    @GetMapping("/admin/liste-stagiaires")
    @JsonView(VueStagiaire.class)
    public List<Stagiaire> listStagiaire() {
        return stagiaireDao.findAll();
    }
}
