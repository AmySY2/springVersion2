package com.gestionnaire.dossier.security;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //pour injecter dans securite demo
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceSite userDetailsServiceSite;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization"); //recuperer le token
        //System.out.println(token);

        if(token != null && token.startsWith("Bearer")){ //token non null et token commence par Bearer

            try {
                //enlever les 7 premier caracter
                String jwt = token.substring(7);

                String identifiant = jwtUtils.getTokenBody(jwt).getSubject(); //recuperer le nom de l'identifiant

                UserDetailsSite userDetails = this.userDetailsServiceSite.loadUserByUsername(identifiant);//recupère la token

                //if true
                if (token != null && jwtUtils.tokenValide(jwt, userDetails)) { //on regarde si son nom et le corps  est valide puis l'enregistrer
                    //on va recuper refaire le test
                    //recuperer les doit
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //recuperer le contexte de securiter deja défini dans securité demo
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            } catch (MalformedJwtException e) {
                System.out.println("Le teken est malformé !");
                e.printStackTrace();
                //filterChain.doFilter(request, response);
            } catch (SignatureException e){
                System.out.println("Le teken à un corp qui ne correspond pas à la signature !");
                e.printStackTrace();
            }catch (Exception e){
                System.out.println("Erreur inconue sur le traitement du token !");
                e.printStackTrace();
            }

        }
        filterChain.doFilter(request, response);//enregistrer la personne
    }
}
