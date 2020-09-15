package com.example.demo.translator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
class DemoWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // AUTHORIZE
        http.authorizeRequests()
                .mvcMatchers("/hello").permitAll()
                .mvcMatchers("/translation").permitAll()
                .mvcMatchers("/hc").permitAll()
                .anyRequest().authenticated()
        ;
        // LOGIN
        http.formLogin()
                .defaultSuccessUrl("/translation")
                //.usernameParameter("username")
                //.passwordParameter("password")
        ;
        // CSRF;
    }
}