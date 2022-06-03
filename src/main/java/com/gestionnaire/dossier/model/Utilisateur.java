package com.gestionnaire.dossier.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.vew.VueClasse;
import com.gestionnaire.dossier.vew.VueRole;
import com.gestionnaire.dossier.vew.VueStagiaire;
import com.gestionnaire.dossier.vew.VueUtilisateur;
import lombok.Data;
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
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //id est autogénerer
    @JsonView({VueUtilisateur.class, VueClasse.class, VueStagiaire.class, VueRole.class})
    private Integer id;

    @JsonView({VueUtilisateur.class, VueClasse.class, VueStagiaire.class, VueRole.class})
    private String nom;

    @JsonView({VueUtilisateur.class, VueClasse.class, VueStagiaire.class, VueRole.class})
    private String prenom;

    @Column(unique = true)
    private String email;

    private  String identifiant;

    private String motDePasse;


    public Utilisateur() {
    }


   @ManyToMany()
   @JoinTable(name = "role_utilisateur",
           joinColumns = @JoinColumn(name = "utilisateur_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
    //@JsonView(VueUtilisateur.class)
    private List<Role> role = new ArrayList<>();

}


