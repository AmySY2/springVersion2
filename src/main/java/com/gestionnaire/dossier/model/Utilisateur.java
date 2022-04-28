package com.gestionnaire.dossier.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class) // générer les tables automatiquement
@Inheritance(strategy = InheritanceType.JOINED) // gerer l'heritage
@Getter
@Setter
public class Utilisateur {

    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //id est autogénerer
    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private  String identifiant;

    private String motDePasse;

    public Utilisateur(Integer id, String nom, String prenom, String email, String identifiant, String motDePasse, List<Role> listeRole) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.listeRole = listeRole;
    }


    public Utilisateur() {
    }

    @ManyToMany
    private List<Role> listeRole = new ArrayList<>();

    public Utilisateur(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

}


