package com.example.security_reactive.services;

import com.example.security_reactive.configs.JwtService;
import com.example.security_reactive.models.User;
import com.example.security_reactive.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final JwtService jwtService;

    public Mono<User> createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Mono<String> login(User user) {
        return reactiveAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        ).map(authentication -> {
            return jwtService.generateToken(user.getUsername());
        }).onErrorResume(e -> Mono.just("False"));
    }
}
