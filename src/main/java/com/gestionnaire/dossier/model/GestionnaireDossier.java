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
@Getter
@Setter
public class GestionnaireDossier extends Utilisateur{



    @OneToMany(mappedBy = "gestionnaireDossier")//on parle de la meme relation
    List<PieceJustificative> pieceJustifives = new ArrayList<>();

    @ManyToMany
    List<ElementDemandable> elementDemandables = new ArrayList<>();

    @ManyToMany(mappedBy = "gestionnaireDossiers")
    List<DemandeInscription> demandeInscriptions = new ArrayList<>();

}
