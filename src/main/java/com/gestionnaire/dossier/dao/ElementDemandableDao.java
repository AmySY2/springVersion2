package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.ElementDemandable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementDemandableDao extends JpaRepository<ElementDemandable, Integer> {

}
