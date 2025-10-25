package com.example.security_reactive.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
@Data
public class User {
    @Id
    private Long id;

    private String username;
    private String password;
    private String role;
}
