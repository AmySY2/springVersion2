package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.Formation;
import com.gestionnaire.dossier.model.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagiaireDao extends JpaRepository<Stagiaire, Integer> {


}
