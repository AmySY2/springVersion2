INSERT INTO role (id, nom) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_GESTINNAIRE_ADMMINISTRATIF'),
(3, 'ROLE_GESTINNAIRE_ABSENCE_RETARD'),
(4, 'ROLE_STAGIAIRE'),
(5, 'ROLE_CANDIDAT'),
(6, 'ROLE_USER');

INSERT INTO utilisateur (id, nom, prenom, identifiant, mot_de_passe) VALUES
(1, 'franky', 'bansept', 'administration', '$2a$10$OlpS/BoZmAJdH9b9tdx9f.rPD57PnXxlalDCwluasjTpzWbqwH9b.'),
(2, 'john', 'doe', 'secretaire', '$2a$10$OlpS/BoZmAJdH9b9tdx9f.rPD57PnXxlalDCwluasjTpzWbqwH9b.'),
(3, 'steeve', 'smith', 'stagiaire', '$2a$10$OlpS/BoZmAJdH9b9tdx9f.rPD57PnXxlalDCwluasjTpzWbqwH9b.'),
(4, 'amy', 'Sy', 'admin', '$2a$10$OlpS/BoZmAJdH9b9tdx9f.rPD57PnXxlalDCwluasjTpzWbqwH9b.');



--INSERT INTO candidat (id, nom, prenom, mot_de_passe) VALUES
--(1, 'franky', 'bansept', '$2a$10$LVQemRjLH14hb8A2sMYW8uis9jYpokNB9c/YHrMWyFyJF9nxtTW2e'),
--(2, 'john', 'doe', '$2a$10$LVQemRjLH14hb8A2sMYW8uis9jYpokNB9c/YHrMWyFyJF9nxtTW2e'),
--(3, 'steeve', 'smith', '$2a$10$LVQemRjLH14hb8A2sMYW8uis9jYpokNB9c/YHrMWyFyJF9nxtTW2e');

INSERT INTO role_utilisateur ( role_id, utilisateur_id) VALUES
(2,1),
(2,2),
(3,2),
(3,3),
(1,4);

INSERT INTO formation (id, description, libele) VALUES
(1, 'niveau1', 'devLog'),
(2, 'niveaur2', 'devWeb');

INSERT INTO classe (id, nom) VALUES
(1,  'devLog'),
(2, 'devWeb');


--INSERT INTO demande_inscription (id, date_inscription, status_dossier, candidat_id, formation_id) VALUES
--(1, '2022-02-01', 'ok', 1, 1),
--(2, '2022-02-02', 'ok', 2, 2),
--(3, '2022-02-01', 'ok', 3, 1);


INSERT INTO stagiaire (id, adresse, division_parcour) VALUES
(1, '8 rue du arène', 'java'),
(2, '8 rue du arène', 'dopnet');



