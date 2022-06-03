package com.gestionnaire.dossier.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.dao.ClasseDao;
import com.gestionnaire.dossier.dao.RoleDao;
import com.gestionnaire.dossier.model.Classe;
import com.gestionnaire.dossier.model.Role;
import com.gestionnaire.dossier.model.Utilisateur;
import com.gestionnaire.dossier.vew.VueClasse;
import com.gestionnaire.dossier.vew.VueRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RoleController {

    private RoleDao roleDao;

    @Autowired
    public RoleController(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @GetMapping("/admin/liste-Role")
    @JsonView(VueRole.class)
    public List<Role> role() {
        return roleDao.findAll();
    }

}


