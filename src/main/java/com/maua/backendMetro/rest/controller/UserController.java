package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Users;
import com.maua.backendMetro.domain.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getUsersByEmail(@RequestParam(required = false) String email) {
        if (email != null) {
            return userRepository.findByEmail(email);
        }
        return userRepository.findAll();  // retorna todos se não houver filtro
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }
}
