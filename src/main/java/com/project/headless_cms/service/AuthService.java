package com.project.headless_cms.service;

import com.project.headless_cms.config.JWTUtil;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWTUtil jwtUtil;

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String login(String email, String password) {
        Users user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}