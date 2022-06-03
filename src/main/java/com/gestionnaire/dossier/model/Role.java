package com.gestionnaire.dossier.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.vew.VueRole;
import com.gestionnaire.dossier.vew.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class) // générer les tables automatiquement
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //id est autogénerer
    @JsonView({VueRole.class, VueUtilisateur.class})
    private Integer id;

    @JsonView({VueRole.class, VueUtilisateur.class})
    private String nom;

    @ManyToMany(mappedBy = "role")
    @JsonView(VueRole.class)
    List<Utilisateur> listeUtilisateur = new ArrayList<>();

   /* public List<Utilisateur> getUtilisateur() {
        return listeUtilisateur;
    }
    public void setUtilisateur(List<Utilisateur> listeUtilisateur) {
        this.listeUtilisateur = listeUtilisateur;
    }*/
}
