package com.example.spring_security.controllers;

import com.example.spring_security.models.User;
import com.example.spring_security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;

    @GetMapping("/home")
    public String home() {
        return "Hello Admin";
    }

    @DeleteMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow();

        if (!user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new Exception("False");
        }

        userRepository.delete(user);
        return "Success";
    }
}
