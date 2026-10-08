package com.backend.chocofruta.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordGenerator {

    @Bean
    public CommandLineRunner generatePassword(PasswordEncoder encoder) {
        return args -> {
            String hash = encoder.encode("admin123");
            System.out.println("BCrypt para admin = " + hash);
        };
    }
}
