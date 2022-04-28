package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class) // générer les tables automatiquement
@Getter
@Setter
public class Stagiaire extends Utilisateur{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Integer id;

    private String adresse;

    private String divisionRan;

    private String divisionParcour;

    private int Telephone;

    public Stagiaire(Integer id, String nom, String prenom, String email, String identifiant, String motDePasse, List<Role> listeRole, Integer id1, String adresse, String divisionRan, String divisionParcour, int telephone) {
        super(id, nom, prenom, email, identifiant, motDePasse, listeRole);
        this.id = id1;
        this.adresse = adresse;
        this.divisionRan = divisionRan;
        this.divisionParcour = divisionParcour;
        Telephone = telephone;
    }

    @OneToMany(mappedBy = "stagiaire")
    private List<EvenementDePresence> liseEvenement = new ArrayList<>();

    @ManyToMany
    private List<Classe> classes = new ArrayList<>();



}
