package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class DemandeInscription extends Utilisateur{


    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //id est autogénerer
    private Integer id;

    private Date dateInscription;

    private String nationnalite;

    private String statusDossier;


    @ManyToOne
    @MapsId("candidat_id")//Clé etranger
    private Candidat candidat;

    @ManyToOne
    @MapsId("formation_id")
    private Formation formation;

    @ManyToMany
    @JoinTable(name = "GestinnairetDossier_DemandeInscription",
            joinColumns = @JoinColumn(name = "demandeInscription_id"),
            inverseJoinColumns = @JoinColumn(name = "gestinnaireDossier_id")
    )
    List<GestionnaireDossier> gestionnaireDossiers = new ArrayList<>();


}
