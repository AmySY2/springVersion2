package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.GestionnaireDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionnaireDossierDao extends JpaRepository<GestionnaireDossier, Integer> {


}
