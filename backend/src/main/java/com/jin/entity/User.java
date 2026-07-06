package com.jin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // BCrypt encoded

    private String nickname;

    private String email;

    private String role; // ROLE_USER, ROLE_MERCHANT, ROLE_ADMIN

    private LocalDateTime createdAt = LocalDateTime.now();
}
