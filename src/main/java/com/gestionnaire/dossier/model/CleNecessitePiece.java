package com.gestionnaire.dossier.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable //est utilisée pour incorporer un type dans une autre entité
public class CleNecessitePiece implements Serializable {

    @Column(name = "pieceJustificative_id")
    private Integer pieceJustificativeId;

    @Column(name = "elementDemandable_id")
    private Integer elementDemandableId;

    @Column(name = "demandeInscription_id")
    private Integer demandeInscriptionId;

    public Integer getPieceJustificatifId() {
        return pieceJustificativeId;
    }

    public void setPieceJustificatifId(Integer pieceJustificatifId) {
        this.pieceJustificativeId = pieceJustificatifId;
    }

    public Integer getElementDemandableId() {
        return elementDemandableId;
    }

    public void setElementDemandableId(Integer elementDemandableId) {
        this.elementDemandableId = elementDemandableId;
    }

    public Integer getDemandeInscriptionId() {
        return demandeInscriptionId;
    }

    public void setDemandeInscriptionId(Integer demandeInscriptionId) {
        this.demandeInscriptionId = demandeInscriptionId;
    }
}
