package com.gestionnaire.dossier.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecuriteSite extends WebSecurityConfigurerAdapter {

    //c'est la classe qui permet de configurer et l'autorisation

    @Autowired
    private DataSource dataSource; //il va voir qu'il a besoin une propriete datasource ....

    @Autowired
    private UserDetailsService userDetailsServiceSite;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  //authentification
        auth
                .userDetailsService(userDetailsServiceSite)
                .passwordEncoder(getPassWordEncoder());
        //la façon de s'autentifier se fait dans la classe userDetailsSerciceDemo
    }




    //  Pour le criptage, on utilisera bcrypt à la place de Md2(il n'est plus sécuriser: peit etre décripté)
    // l'interet de bcrype est qu'il est plus securiser(génere un nouveau criptage à chaque requette)

    @Bean
    public PasswordEncoder getPassWordEncoder() {
        //return NoOpPasswordEncoder.getInstance(); //il va pas faire d'encodage
        return  new BCryptPasswordEncoder();
    }

    @Bean //permet de dire explicitement que l’on utilise un mot de passe en clair.
    public AuthenticationManager getPasswordEncoding() throws Exception {
        return authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception { //configurer les autorisation
        //super.configure(http);
        //toute les url qui commence par /admin il faut qu'il ont le role dr admin
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/connexion").permitAll()
                .antMatchers("/inscription", "/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/gestionnaire_dossier/**").hasRole("GESTINNAIRE_ADMMINISTRATIF")
                .antMatchers("/gesonnaire_presence/**").hasRole("GESTINNAIRE_ABSENCE_RETARD")
                .antMatchers("/stagiaire/**").hasRole("STAGIAIRE")
                .antMatchers("/**").hasAnyRole("STAGIAIRE","ADMIN","GESTINNAIRE_ADMMINISTRATIF","GESTINNAIRE_ABSENCE_RETARD")
                .and().exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);//filtrer toute les requettes
    }

    @Bean
    public CorsConfigurationSource configurtionCrossOrigin() {
        CorsConfiguration maConfiguration = new CorsConfiguration();

        maConfiguration.setAllowedOrigins(List.of("*"));
        maConfiguration.setAllowedMethods(List.of("HEAD","GET", "POST", "PUT", "DELETE", "PATCH"));
        maConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", maConfiguration);
        return source;
    }


}