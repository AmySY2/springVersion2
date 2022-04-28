package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class GestionnaireDossier extends Utilisateur{



    @OneToMany(mappedBy = "gestionnaireDossier")//on parle de la meme relation
    List<PieceJustificative> pieceJustifives = new ArrayList<>();

    @ManyToMany
    List<ElementDemandable> elementDemandables = new ArrayList<>();

    @ManyToMany(mappedBy = "gestionnaireDossier")
    List<DemandeInscription> demandeInscriptions = new ArrayList<>();

}
