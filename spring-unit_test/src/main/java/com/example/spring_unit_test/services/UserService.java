package com.example.spring_unit_test.services;

import org.springframework.stereotype.Service;

import com.example.spring_unit_test.models.User;
import com.example.spring_unit_test.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepositoryl;

    public User getUserById(Long id) {
        return userRepositoryl.findById(id)
                .orElseThrow();
    }
}
