package com.gestionnaire.dossier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Motif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //id est autogénerer
    private Integer id;

    private String libelleMotif;

    private String textMotif;

    @OneToMany(mappedBy = "motif")
    List<EvenementDePresence> evenementDePresences = new ArrayList<>();



}
