package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitUsersConfig {
	private final PasswordEncoder passwordEncoder;
	
	public InitUsersConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User(null, "admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN");
                userRepository.save(admin);
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User(null, "user", passwordEncoder.encode("user123"), "ROLE_USER");
                userRepository.save(user);
            }
        };
    }
}
