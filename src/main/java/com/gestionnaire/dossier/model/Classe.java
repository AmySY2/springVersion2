package com.gestionnaire.dossier.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.vew.VueClasse;
import com.gestionnaire.dossier.vew.VueStagiaire;
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
    @JsonView({VueClasse.class, VueStagiaire.class})
    private Integer id;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String parcours;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String Nom;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String description;

    public Classe() {
    }

    public Classe(String parcours, String nom, String description) {
        this.parcours = parcours;
        this.Nom = nom;
        this.description = description;
    }

    public Classe(Integer id, String nom) {
        this.id = id;
        Nom = nom;
    }

    @ManyToMany
    @JsonView(VueClasse.class)
    @JoinTable(
            name = "stagiaire_classe",
            joinColumns = @JoinColumn(name = "classe_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaire_id"))
    private List<Stagiaire> stagiaires = new ArrayList<>();

}
