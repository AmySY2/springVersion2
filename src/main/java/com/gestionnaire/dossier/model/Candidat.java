package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("2")
@Getter
@Setter
public class Candidat extends Utilisateur{


    @OneToMany(mappedBy = "candidat")
    List<DemandeInscription> demandeInscriptions = new ArrayList<>();
}
