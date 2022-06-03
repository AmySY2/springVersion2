package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class) // générer les tables automatiquement
@Inheritance(strategy = InheritanceType.JOINED) // gerer l'heritage
@Getter
@Setter
public class EvenementDePresence {

    @Id
    @GeneratedValue
    private  Integer id;

    @Temporal(TemporalType.DATE) //indiquer si c'est date seulement oubien date et heur à la fois
    private Date dateDeSaisie;

    private String evaluation;

    private Date dateEvaluation;

    public EvenementDePresence(Integer id, Date dateDeSaisie, Date dateEvaluation, String evaluation) {
        this.id = id;
        dateDeSaisie = dateDeSaisie;
        dateEvaluation = dateEvaluation;
        evaluation = evaluation;
    }

    public EvenementDePresence() {
    }

    @ManyToOne
    @MapsId("stagiaire_id")
    private Stagiaire stagiaire;

    @OneToMany(mappedBy = "evenementDePresence")
    List<PieceJustificative> pieceJustifives = new ArrayList<>();

    @ManyToOne
    @MapsId("motif_id")
    private Motif motif;

    @ManyToOne
    @MapsId("gestionnairePresence_id")
    private GestionnairePresence gestionnairePresence;


}
