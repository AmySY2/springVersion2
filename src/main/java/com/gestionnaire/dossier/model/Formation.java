package com.gestionnaire.dossier.model;

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
    private Integer id;

    private String libele;

    private  String description;

    private String Status;

    @ManyToMany
    @JoinTable(name = "Formation_ElementDemander",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "elementDemandable_id")
    )
    List<ElementDemandable> elementDemandables = new ArrayList<>();


    @OneToMany(mappedBy = "formation")
    List<DemandeInscription> demandeInscriptions = new ArrayList<>();
}
