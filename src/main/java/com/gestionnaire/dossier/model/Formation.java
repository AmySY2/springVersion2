package com.gestionnaire.dossier.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.vew.VueClasse;
import com.gestionnaire.dossier.vew.VueFormation;
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
public class Formation {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @JsonView(VueFormation.class)
    private Integer id;

    @JsonView(VueFormation.class)
    private String libele;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private  String description;

    @JsonView({VueClasse.class, VueStagiaire.class})
    private String Status;

    public Formation(Integer id, String libele, String description) {
        this.id = id;
        this.libele = libele;
        this.description = description;
    }

    @ManyToMany
    @JoinTable(name = "Formation_ElementDemander",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "elementDemandable_id")
    )
    List<ElementDemandable> elementDemandables = new ArrayList<>();


    @OneToMany(mappedBy = "formation")
    List<DemandeInscription> demandeInscriptions = new ArrayList<>();
}
