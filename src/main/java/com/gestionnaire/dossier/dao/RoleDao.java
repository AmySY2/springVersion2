package com.gestionnaire.dossier.dao;

import com.gestionnaire.dossier.model.Classe;
import com.gestionnaire.dossier.model.Role;
import com.gestionnaire.dossier.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query("FROM Role r JOIN FETCH r.listeUtilisateur WHERE r.nom = :nom") //model aulieu de table. JOIN fETCH =charger la liste des roles
    Optional<Role> findByNonWithUtilisateur(@Param("nom") String nom);
}
