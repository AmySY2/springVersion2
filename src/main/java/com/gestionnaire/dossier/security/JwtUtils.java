package com.gestionnaire.dossier.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service //@Service == @Repository == @Componante
public class JwtUtils { //pour gerrer les token

    //recuperer la secret
    @Value("${secret}")//rechercher dans l'application.proporties
    private String secret;

    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody(); //getBody() renvoi un objet de type claims


    }

    //générer un token
    public String generateToken(UserDetails userDetails){

        UserDetailsSite userDetailsSite = (UserDetailsSite) userDetails;

        String listeDroit = userDetails
                .getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(";"));


        //pour stocker l'id de l'utilisateur
    Map<String, Object> data = new HashMap<>();
    data.put("id", userDetailsSite.getUtilisateur().getId());
    data.put("identifiant", userDetailsSite.getUtilisateur().getIdentifiant());
    data.put("roles", listeDroit);




        return Jwts.builder()
                .setClaims(data)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    //est ce que la signature elle est bonne
    public  boolean tokenValide(String token, UserDetails userDetails){

        Claims claims = getTokenBody(token);

        //verifier si le token est identique avec celui qui est connecter
        return  (claims.getSubject().equals(userDetails.getUsername()));

    }
}

