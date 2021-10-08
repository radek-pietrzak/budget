//package com.home.budget.security;
//
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakSecurityContextRequestFilter;
//import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {
//
//    @Autowired
//    void configureGlobal(AuthenticationManagerBuilder auth) {
//        KeycloakAuthenticationProvider keycloakProvider = keycloakAuthenticationProvider();
//        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
//        authorityMapper.setPrefix("ROLE_");
//        authorityMapper.setConvertToUpperCase(true);
//        keycloakProvider.setGrantedAuthoritiesMapper(authorityMapper);
//        auth.authenticationProvider(keycloakProvider);
//    }
//
//
//    @Bean
//    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }
//
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//
//        http.authorizeRequests()
////                .antMatchers("/home-budget/*", "/swagger-ui/*")
////                .hasRole("ADMIN")
//                .anyRequest()
//                .permitAll();
//    }
//
//
//}
