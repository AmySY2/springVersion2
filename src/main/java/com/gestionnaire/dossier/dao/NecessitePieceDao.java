package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.CleNecessitePiece;
import com.gestionnaire.dossier.model.NecessitePiece;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NecessitePieceDao extends JpaRepository<NecessitePiece, CleNecessitePiece> {

    Optional<NecessitePieceDao> findByPieceJustificativeIdAndElementDemandableIdAndDemandeInscriptionId(
            Integer pieceJustificativeId,
            Integer elementDemandableId,
            Integer demandeInscriptionId
    );

    void deleteByPieceJustificativeIdAndElementDemandableIdAndDemandeInscriptionId(
            Integer pieceJustificativeId,
            Integer elementDemandableId,
            Integer demandeInscriptionId
    );
}
