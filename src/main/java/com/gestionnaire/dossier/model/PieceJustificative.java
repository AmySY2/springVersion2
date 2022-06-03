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
public class PieceJustificative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private int taille;

    private  String format;

    private String Status;

    @Temporal(TemporalType.DATE)
    private Date dateDepot;

    private String message;

    public PieceJustificative(Integer id, int taille, String format, String status, Date dateDepot, String message) {
        this.id = id;
        this.taille = taille;
        this.format = format;
        Status = status;
        this.dateDepot = dateDepot;
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "evenementDePresence_id")
    private EvenementDePresence evenementDePresence;

    @ManyToOne
    @JoinColumn(name = "gestionnairePresence_id")
    private  GestionnairePresence gestionnairePresence;

    @ManyToOne
    @JoinColumn(name = "gestionnaireDossier_id")
    private GestionnaireDossier gestionnaireDossier;

}
