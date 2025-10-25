package com.example.spring_reactive.controllers;

import com.example.spring_reactive.configs.JwtService;
import com.example.spring_reactive.models.User;
import com.example.spring_reactive.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public Mono<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestBody User user) {
        return reactiveAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        ).map(authentication -> {
            return jwtService.generateToken(user.getUsername());
        }).onErrorResume(e -> {
            return Mono.just("False");
        });
    }

}
