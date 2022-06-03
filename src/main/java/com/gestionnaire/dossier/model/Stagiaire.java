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
@EntityListeners(AuditingEntityListener.class) // générer les tables automatiquement
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("2")
@Getter
@Setter
public class Stagiaire extends Utilisateur{

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String adresse;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String divisionRan;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String divisionParcour;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String Telephone;

    @OneToMany(mappedBy = "stagiaire")
    private List<EvenementDePresence> liseEvenement = new ArrayList<>();

    @ManyToMany
    @JsonView(VueStagiaire.class)
    private List<Classe> classes = new ArrayList<>();



}
