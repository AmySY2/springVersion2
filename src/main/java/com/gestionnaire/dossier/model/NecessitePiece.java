package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;



@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(CleNecessitePiece.class)
@Table(name = "Necessite_Piece")
@Getter
@Setter
public class NecessitePiece {

    @Id
    private Integer pieceJustificativeId;

    @Id
    private Integer elementDemandableId;

    @Id
    private Integer demandeInscriptionId;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("pieceJustificative_id")
    @JoinColumn(name = "pieceJustificative_id")
    private PieceJustificative pieceJustificative;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("elementDemandable_id")
    @JoinColumn(name = "elementDemandable_id")
    private ElementDemandable elementDemandable;

    @ManyToOne(fetch=FetchType.LAZY /*, mappedBy = "demandeInscription"*/)
    @MapsId("demandeInscription_id")
    @JoinColumn(name = "demandeInscription_id")
    private DemandeInscription demandeInscription;

}
