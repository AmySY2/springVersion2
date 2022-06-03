package com.gestionnaire.dossier.security;

import com.gestionnaire.dossier.dao.UtilisateurDao;
import com.gestionnaire.dossier.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceSite implements UserDetailsService {
    //c'est la classe qui va englober utilisateur pour q'il soit compris par spring

    private UtilisateurDao utilisateurDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceSite(UtilisateurDao utilisateurDao){
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetailsSite loadUserByUsername(String username) throws UsernameNotFoundException {

        //pour recuperer les informations
        Utilisateur utilisateur = utilisateurDao
                .findByIdentifiantWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("Mauvais pseudo / mot de passe"));

        UserDetailsSite userDetailsSite = new UserDetailsSite(utilisateur);

        //System.out.println(jwtUtils.generateToken(userDetailsDemo));


        return userDetailsSite;
    }
}