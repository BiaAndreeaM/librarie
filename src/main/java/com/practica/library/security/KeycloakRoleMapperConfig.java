//package com.practica.library.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
//import java.util.*;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Configuration
//public class KeycloakRoleMapperConfig {
//
//    private final Logger logger = Logger.getLogger(KeycloakRoleMapperConfig.class.getName());
//
//    public KeycloakRoleMapperConfig() {
//        logger.info("Bean KeycloakRoleMapperConfig created");
//    }
//
//
//    /* convertor bean used in resource server scenarios, to extract roles from JWT */
//    public JwtAuthenticationConverter jwtRolesConverter() {
//
//        logger.info("jwtRolesConverter() called");
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//
//        converter.setJwtGrantedAuthoritiesConverter(this::extractRealmRoles);
//
//        return converter;
//    }
//
//    public Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {
//
//        logger.info("extractRealmRoles called");
//        logger.info("token: " + jwt.getTokenValue());
//        logger.info("token value can be extracted & inspected on jwt.io");
//
//        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
//
//        if (realmAccess == null || !(realmAccess.get("roles") instanceof List<?> roles)) {
//            return List.of();
//        }
//
//        logger.info("extracted roles: " + roles);
//
//        return roles.stream()
//                .map(String::valueOf)
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
//    }
//}
