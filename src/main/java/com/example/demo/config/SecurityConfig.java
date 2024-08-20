package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/*
 * declaramos que esto es una clase de configuracion de seguridad
 * gracias a la anotacion enablewebsecurity entonces spring interpreta que esta clase
 * contendra configuraciones de seguridad con los beans
 */

/*autentificaciones obligatorias
 * el loggin 
 *  el manejo de errores al ingreso
 * 
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // reglas de autentificacion podemos declarar rutas a autorizar
    // añadimos los filters
    // que van a interceptar cualquier rerquest o las que les asignemos en este caso
    // usamos anyRequests
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(authz -> authz // que todas las request sean autenticadas con el protocolo basico
                .anyRequest()
                .authenticated()); // Configuración de autenticación básica user password uso el
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //with SessionCreationPolicy you specify if you want for stateless or statesful with session creation policy
        //so by doing it statless what we are doing is accessing in a new recourse in a new session and a new request to authenticate

        return http.build(); // customzier para declarar por default la configuracion
    }

    //how does work all filters configs

    //we use the httpSecurityObject
    //thats contains some methods and one of them is csrf thats we are passing a customizer and disabled
    //

    Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
        @Override
        public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
            //  customizer is a part of httpsecurity object package
            // on every httpsecurity config customization we have an object like this
            // in this case we disable the httpconfig from csrf 

            httpSecurityCsrfConfigurer.disable();

        }
    }
}   
