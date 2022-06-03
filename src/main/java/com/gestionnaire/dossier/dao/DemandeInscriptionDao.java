package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.DemandeInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeInscriptionDao extends JpaRepository<DemandeInscription, Integer> {

}
