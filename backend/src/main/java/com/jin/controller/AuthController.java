package com.jin.controller;

import com.jin.dto.LoginRequest;
import com.jin.dto.LoginResponse;
import com.jin.entity.User;
import com.jin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRole("ROLE_USER");
        userRepository.save(u);
        // token placeholder
        LoginResponse resp = new LoginResponse(u.getId(), u.getUsername(), u.getRole(), "demo-token");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<User> ou = userRepository.findByUsername(req.getUsername());
        if (ou.isEmpty()) return ResponseEntity.status(401).body("用户名或密码错误");
        User u = ou.get();
        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            return ResponseEntity.status(401).body("用户名或密码错误");
        }
        // For now return a demo token; JWT will be added later
        LoginResponse resp = new LoginResponse(u.getId(), u.getUsername(), u.getRole(), "demo-token");
        return ResponseEntity.ok(resp);
    }
}
