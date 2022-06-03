package com.gestionnaire.dossier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@EnableJpaAuditing    //activer
@SpringBootApplication
public class GestionnaireDossierApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {

        SpringApplication.run(GestionnaireDossierApplication.class, args);
    }

}
