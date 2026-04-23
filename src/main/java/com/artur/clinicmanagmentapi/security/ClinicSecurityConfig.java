package com.artur.clinicmanagmentapi.security;

import com.artur.clinicmanagmentapi.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.HttpServerErrorException;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class ClinicSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider(userService);
//        auth.setUserDetailsService(userService); deprecated
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/patients").hasRole("NURSE")
                        .requestMatchers(HttpMethod.GET, "/api/patients/**").hasRole("NURSE")
                        .requestMatchers(HttpMethod.PUT, "/api/patients").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.POST, "/api/patients").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/patients/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/patients/**").hasRole("ADMIN")

                        .requestMatchers("/error").permitAll()
        );

        // use http basic auth
        http.httpBasic(Customizer.withDefaults());

        // disable csrf
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}


 /*  @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

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
*/


