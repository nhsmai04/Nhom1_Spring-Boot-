package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests(authz -> authz
	    	    .requestMatchers("/", "/login", "/css/**").permitAll()
	    	    .requestMatchers(HttpMethod.POST, "/api/books/**").hasRole("ADMIN")
	    	    .anyRequest().authenticated()
        	)
            .csrf(csrf -> csrf.disable()) 
            .formLogin(form -> form
        		.loginPage("/login") 
                .loginProcessingUrl("/perform_login") 
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()); 

        return http.build();
    }
}
