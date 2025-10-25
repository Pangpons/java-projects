package com.example.spring_unit_test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_unit_test.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
