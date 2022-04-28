package com.gestionnaire.dossier.model;


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

    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //id est autogénerer
    private Integer id;
    private String nom;

    @ManyToMany()
            @JoinTable(name = "Role_Utilisateur",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur"))
    List<Stagiaire> stagiaires = new ArrayList<>();
}
