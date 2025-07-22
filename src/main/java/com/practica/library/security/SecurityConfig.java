//package com.practica.library.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final KeycloakRoleMapperConfig keycloakRoleMapperConfig;
//
//    public SecurityConfig(KeycloakRoleMapperConfig keycloakRoleMapperConfig) {
//        this.keycloakRoleMapperConfig = keycloakRoleMapperConfig;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        http
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry
//                                .requestMatchers("/book/**").hasRole("ADMIN")
//                                .anyRequest().authenticated()
//                )
//
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(
//                                keycloakRoleMapperConfig.jwtRolesConverter())));
//
//        return http.build();
//    }
//}
