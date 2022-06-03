package com.gestionnaire.dossier.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gestionnaire.dossier.dao.UtilisateurDao;
import com.gestionnaire.dossier.model.Role;
import com.gestionnaire.dossier.model.Utilisateur;
import com.gestionnaire.dossier.security.JwtUtils;
import com.gestionnaire.dossier.security.UserDetailsServiceSite;
import com.gestionnaire.dossier.vew.VueRole;
import com.gestionnaire.dossier.vew.VueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {

    private UtilisateurDao utilisateurDao;

    @Autowired //recuperer l'instence de cette class
    JwtUtils jwtUtils;

    @Autowired //au démarage de l'app il va chercher quelque chose de type authenticationManager
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDetailsServiceSite userDetailsServiceSite;


    @Autowired //pour dir que on veux une injection de dépendance
    public UtilisateurController(UtilisateurDao utilisateurDao){ //passé pour les dépendances
        //System.out.println("toto");
        this.utilisateurDao = utilisateurDao; //affecter à une propriété
    }

    @PostMapping("/inscription")
    public String inscription(@RequestBody Utilisateur utilisateur){

        utilisateur.setMotDePasse(encoder.encode(utilisateur.getMotDePasse()));

        Role roleUser = new Role();
        roleUser.setId(1);
        utilisateur.getRole().add(roleUser);

        utilisateurDao.save(utilisateur);

        return "utilisateur créé";
    }

    @PostMapping("/connexion") //le but est de nous renvoyer un token qui existe dans la base de données
    public Map<String,String > connextion(@RequestBody Utilisateur utilisateur) throws  Exception{ //recuperer un utilisateur


        try {

            //verifier si il existe bien, ensuite recuper le nom et mot de passe
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    utilisateur.getIdentifiant(),
                    utilisateur.getMotDePasse()));

        }catch (BadCredentialsException e){
            Map<String,String > retour = new HashMap<>();
            retour.put("erreur", "Mauvais login /mot de passe");
            throw new Exception(e);
        }

        //trenseformer en token
        UserDetails userDetails = userDetailsServiceSite.loadUserByUsername(utilisateur.getIdentifiant());

        //recuperer le token
        Map<String,String > retour = new HashMap<>();
        retour.put("token", jwtUtils.generateToken(userDetails));
        return retour;
    }


    @GetMapping("/liste-utilisateur")
    @JsonView(VueUtilisateur.class)
    public List<Utilisateur> listeUtilisateur() {

        return this.utilisateurDao.findAll() ;
    }

    @GetMapping("/utilisateur_par_nom/{nom}")
    public Utilisateur utilisateurParIdentifiant(@PathVariable String nom){
        return this.utilisateurDao.findByNom(nom).orElse(null);//returne null si il ya pas un nouvelle utilisateur
    }



    @GetMapping("/utilisateur/{id}") //{id} : proprieté dynamique
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<Utilisateur> utilisateur(@PathVariable Integer id){    //@PathVariable  pour recuperé id
        Optional<Utilisateur> retour = this.utilisateurDao.findById(id);

        if (retour.isPresent()){
            return ResponseEntity.ok(retour.get());
        }else{
            return ResponseEntity.noContent().build();
        }

        //return this.utilisateurDao.findById(id).orElse(null);
    }


    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> createUtilisateur(
            @RequestBody Utilisateur utilisateur,
            @RequestHeader("Authorization") String jwt
    ){ // verier si lcelui si modifier est elle meme connecter

        String token = jwt.substring(7);

        int idUtilisateurConnecter = (int)jwtUtils.getTokenBody(token).get("id");


        String droits = (String) jwtUtils.getTokenBody(token).get("droits");

        if(droits.contains("ROLE_ADMIN") || idUtilisateurConnecter == utilisateur.getId()) {

            //recupérer en json (a ajouter dans Postman)

            //System.out.println(utilisateur.getPrenom());
            Optional<Utilisateur> ancienUtilisateur = utilisateurDao.findById(utilisateur.getId()); //recuperer l'utilisateur

            if (ancienUtilisateur.isPresent()) {
                ancienUtilisateur.get().setPrenom(utilisateur.getPrenom());
                this.utilisateurDao.save(utilisateur);
                return ResponseEntity.ok(ancienUtilisateur.get());
            }


            return ResponseEntity.noContent().build();
        }else{
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /*@DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Integer> deleteUtilisateur(@PathVariable int id){ //retourne un entier

        ////
        ///

        if(utilisateurDao.existsById(id) ){
            this.utilisateurDao.deleteById(id);
            return  ResponseEntity.ok(id);
            //est ce que l'utilisateur existe
        }else
        return  ResponseEntity.noContent().build();
    }*/

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable int id){ //retourne un entier

        ////
        Optional<Utilisateur> utilisateurASupprimer = utilisateurDao.findById(id);

        if(utilisateurASupprimer.isPresent() ){
            this.utilisateurDao.deleteById(id);
            return  ResponseEntity.ok(utilisateurASupprimer.get());
            //est ce que l'utilisateur existe
        }else
            return  ResponseEntity.noContent().build();
    }

}
