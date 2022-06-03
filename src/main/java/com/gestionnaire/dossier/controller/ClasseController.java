package com.gestionnaire.dossier.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.dao.ClasseDao;
import com.gestionnaire.dossier.model.Classe;
import com.gestionnaire.dossier.vew.VueClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ClasseController {

    private ClasseDao classeDao;

    @Autowired
    public ClasseController(ClasseDao classeDao) {
        this.classeDao = classeDao;
    }

    @GetMapping("/admin/liste-Classe")
    @JsonView(VueClasse.class)
    public List<Classe> listClasse() {
        return classeDao.findAll();
    }
}


