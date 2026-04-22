package com.artur.clinicmanagmentapi.security;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.HttpServerErrorException;

@Configuration
public class ClinicSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test")
                .roles("NURSE")
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test")
                .roles("NURSE", "DOCTOR")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test")
                .roles("NURSE", "DOCTOR", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(mary, john, susan);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/patients").hasRole("NURSE")
                        .requestMatchers(HttpMethod.GET, "/api/patients/**").hasRole("NURSE")
                        .requestMatchers(HttpMethod.PUT, "/api/patients").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.POST, "/api/patients").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/patients/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/patients/**").hasRole("ADMIN")
        );

        // use http basic auth
        http.httpBasic(Customizer.withDefaults());

        // disable csrf
        http.csrf(csrf-> csrf.disable());

        return http.build();
    }

}
