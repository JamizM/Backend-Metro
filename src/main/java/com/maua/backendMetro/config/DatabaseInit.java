package com.maua.backendMetro.config;

import com.maua.backendMetro.domain.entity.Users;
import com.maua.backendMetro.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInit {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("john.doe@example.com").isEmpty()) {
                Users user = new Users();
                user.setFirstName("Pedro");
                user.setPassword("12345");
                user.setEmail("Pedro@gmail.com");
                userRepository.save(user);
                System.out.println("Default user added to the database");
            } else {
                System.out.println("Default user already exists in the database");
            }
        };
    }
}
