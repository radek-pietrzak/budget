//package com.home.budget.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/v2/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//
//        http
//                .csrf()
//                .disable();
//
//    }
//}
