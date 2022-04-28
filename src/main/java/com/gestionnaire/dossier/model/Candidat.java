package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Candidat extends Utilisateur{

    public Candidat(Integer id, String nom, String prenom, String email, String identifiant, String motDePasse, List<Role> listeRole) {
        super(id, nom, prenom, email, identifiant, motDePasse, listeRole);
    }

    @OneToMany(mappedBy = "candidat")
    List<DemandeInscription> demandeInscriptions = new ArrayList<>();
}
