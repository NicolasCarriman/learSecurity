package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authz -> authz // que todas las request sean autenticadas con el protocolo basico
                        .anyRequest()
                        .authenticated() // Configuración de autenticación básica user password uso el
                ).httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // with SessionCreationPolicy you specify if you want for stateless or statesful
        // with session creation policy
        // so by doing it statless what we are doing is accessing in a new recourse in a
        // new session and a new request to authenticate

        return http.build(); // customzier para declarar por default la configuracion
    }

    // how does work all filters configs
    // we use the httpSecurityObject
    // thats contains some methods and one of them is csrf thats we are passing a
    // customizer and disabled
    // Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new
    // Customizer<CsrfConfigurer<HttpSecurity>>() {
    // @Override
    // public void customize(CsrfConfigurer<HttpSecurity>
    // httpSecurityCsrfConfigurer) {
    // // customizer is a part of httpsecurity object package
    // // on every httpsecurity config customization we have an object like this
    // // in this case we disable the httpconfig from csrf
    // httpSecurityCsrfConfigurer.disable();
    // }
    // }

    /*
     * when you want to handle something 
     * you needs to create a @Bean to be able to dependency injection
     * the authentication manager will talk to the authentication provider
     * we use the authentication manager because is the way to configure the security system to
     * implement JWT authentication by default this sistem is not implemented and is used default manager so
     * in this case we config authenticationManager to customize handling of authentication
     */

    // @Bean
    // AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //     return  config.getAuthenticationManager();
    // }

    /*
     * User detail service is used by daoAutenticationProvider to recover user data information
     * is used to retrieving a username, a password and other attributes for authenticating
     * @UserDetailsService is an interface and it mean thats we cant return as a new Object 
     */

    @Bean
    UserDetailsService UserDetailService() {
        DaoAuthentificationProvider test = new DaoAuthentificationProvider();
        return test.getUserDetailsService();
    }
}



/*
 * #DaoAuthentificationProvider performs authentication based on the user name and password
 * it requires PasswordEncoder and 
 * encrypted with becrtypt
 * use userDetailService @UserDetailsService interface 
 * #userDetailService get roles autorites expired account is enabled
 *  that means we need implment this interface 
 */ 


 // INTRODUCTION TO ARCHITECTURE BEHAVIOUR
/*
 * httprequest to your api
 * before the request reaches to the servlet #REACHES:LLEGAR
 * it goes through a series of security filters (SECURITY_FILTER_CHAIN) 
 * so this filters use the configuration that is highly customizable through HTTPSECURITY OBJECT that allows configs
 * like csrf, authentication, routes and roles
 */

 /*
  * JWT TOKEN FILTER
  * jwt token filter intercept the request to validate the token 
  * then this filter create the username and password autentication token,
  * so that ways is to perform authentication, the object wich is responsible for performing authentication
  the Authentication Manager 
  */


/*
 * AUTHENTICATION MANAGER:
 * interface thats attepmts to 
 * auth manager is an interface that is implemented by provider manager class
 * provider manager takes the request from authentication 
 * and it tries to perfom the authentication 
 * based of the provided itself, we hae differents providers
 * #DaoAuthentificationProvider performs authentication based on the user name and password
 */

 /*
  * SECURITY CONTEXT HOLDER
  * detail of the current autenticated user if succed autentication add to this context else remove
  */