package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Users;
import com.maua.backendMetro.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }
}
