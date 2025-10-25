package com.example.security_reactive.controllers;

import com.example.security_reactive.models.User;
import com.example.security_reactive.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public Mono<User> register(@RequestBody User user) {
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestBody User user) {
        return authService.login(user);
    }
}
