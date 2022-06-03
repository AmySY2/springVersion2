package com.gestionnaire.dossier.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.dao.FormationDao;
import com.gestionnaire.dossier.model.Formation;
import com.gestionnaire.dossier.vew.VueFormation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class FormationController{

    private FormationDao formationDao;

    @Autowired
    public FormationController(FormationDao formationDao) {
        this.formationDao = formationDao;
    }

    @GetMapping("/admin/liste-formation")
    @JsonView(VueFormation.class)
    public List<Formation> listFormation() {
        return formationDao.findAll();
    }

    @PostMapping("/admin/formation")
    public ResponseEntity createFormation(@RequestBody Formation formation){ //recupérer en json (a ajouter dans Postman)

        //System.out.println(Formation.getNom());

        this.formationDao.saveAndFlush(formation);//pour recuperer le dernier Id

        return ResponseEntity
                .created(URI.create("/formation/" + formation.getId()))
                .build();
    }
}


