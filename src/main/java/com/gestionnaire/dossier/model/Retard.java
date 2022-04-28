package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Retard extends EvenementDePresence{

    private Date dateHeureRetard;

    public Retard(Date dateHeureRetard) {
        this.dateHeureRetard = dateHeureRetard;
    }

    public Retard() {
    }

}
