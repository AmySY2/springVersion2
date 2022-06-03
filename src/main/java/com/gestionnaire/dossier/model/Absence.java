package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class Absence extends EvenementDePresence{


    private Date dateDebut;

    private Date dateFin;

    public Absence(Integer id, Date dateDeSaisie, Date dateEvaluation, String evaluation, Date dateDebut, Date dateFin) {
        super(id, dateDeSaisie, dateEvaluation, evaluation);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Absence(Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


}
