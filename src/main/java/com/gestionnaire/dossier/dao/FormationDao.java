package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationDao extends JpaRepository<Formation, Integer> {


}
