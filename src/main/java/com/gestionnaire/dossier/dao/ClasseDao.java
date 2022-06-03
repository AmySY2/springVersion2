package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseDao extends JpaRepository<Classe, Integer> {


}
