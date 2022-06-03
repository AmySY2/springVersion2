package com.gestionnaire.dossier.security;

import com.gestionnaire.dossier.model.Role;
import com.gestionnaire.dossier.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsSite implements UserDetails {

    //connecteur utilisateur

    private Utilisateur utilisateur;

    public UserDetailsSite(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();

        for(Role role : utilisateur.getRole()){
            listeAuthority.add(new SimpleGrantedAuthority(role.getNom()));
        }
        return listeAuthority;
    }

    @Override
    public String getPassword() {
        return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return utilisateur.getIdentifiant();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


