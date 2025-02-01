package com.example.backend_challenge_tecnico_techforb.config;

import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @Autowired
    private  JWTFilter jwtAuthenticationFilter;

    @Autowired
    private  AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        return http
                .csrf(csrf->
                        csrf.disable())  //desabilitamos el token por defecto que tiene spring
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()//Permito todo lo que venga a la ruta /auth
                                .requestMatchers(   "/auth/**",            // Permitir autenticación pública
                                        "/v3/api-docs/**",     // OpenAPI docs
                                        "/swagger-ui/**",      // Recursos Swagger UI
                                        "/swagger-ui.html",    // Entrada Swagger UI
                                        "/doc/**" ).permitAll()
                                .anyRequest().authenticated() //autentico todas las demas rutas
                )
                .sessionManagement(sessionManagement->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
