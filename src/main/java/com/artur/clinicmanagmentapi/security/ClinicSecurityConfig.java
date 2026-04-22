package com.artur.clinicmanagmentapi.security;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
}
