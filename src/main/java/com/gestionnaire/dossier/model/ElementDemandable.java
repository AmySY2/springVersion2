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
public class ElementDemandable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private  String Libele;

    private String Remarque;

    private Date description;

    private String message;

    @ManyToMany
    @JoinTable( name = "ElementDemandable_GestionDossier",
            joinColumns = @JoinColumn(name = "elementDemandable_id"),
            inverseJoinColumns = @JoinColumn(name = "gestionDossier_id")
    )
    List<GestionnaireDossier> gestionnaireDossier = new ArrayList<>();

    @ManyToMany
    List<Formation> formations = new ArrayList<>();

}
