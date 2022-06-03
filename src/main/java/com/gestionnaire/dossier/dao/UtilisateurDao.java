package com.gestionnaire.dossier.dao;
import com.gestionnaire.dossier.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByNom(String nom); //optionnel permùet de gerer le fait que le result peut etre null

    @Query("FROM Utilisateur u LEFT JOIN FETCH u.role WHERE u.identifiant = :identifiant") //model aulieu de table. JOIN fETCH =charger la liste des roles
    Optional<Utilisateur> findByIdentifiantWithRoles(@Param("identifiant") String identifiant); //@Param("nom") = affecter nom dessous, recuperer parrapport au nom


    /*@Query(value = "SELECT count(*) FROM utilisateur", nativeQuery = true)
    int compterUtilisateur() ;

    @Query(value = "SELECT as nbUtilisateur count(*), max(id) as maximunId FROM utilisateur", nativeQuery = true)
    Map<String, objet> compterUtilisateur()
    //Map = une collection (de type Map); as = un aliage
    //ma premiere clé de mon resulat est un nbUtilisateur et la deuxième clé un maximunId
    */

    //retourner une liste utilisateur
   /* @Query(value = "SELECT  * utilisateur", nativeQuery = true)
    List<Map<String, objet>> compterUtilisateur()*/
}
