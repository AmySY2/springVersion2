package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Classe {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    private String parcours;

    private String Nom;

    private String description;

    public Classe() {
    }

    public Classe(Integer id, String parcours, String nom, String description) {
        this.id = id;
        this.parcours = parcours;
        this.Nom = nom;
        this.description = description;
    }



    @ManyToMany
    @JoinTable(
            name = "stagiaire_classe",
            joinColumns = @JoinColumn(name = "classe_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaire_id"))
    private List<Stagiaire> stagiaires = new ArrayList<>();

}
