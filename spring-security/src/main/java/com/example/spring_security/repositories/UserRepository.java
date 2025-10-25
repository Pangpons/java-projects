package com.example.spring_security.repositories;

import com.example.spring_security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
