package com.project.headless_cms.service;

import com.project.headless_cms.config.JWTUtil;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.model.UserRole;
import com.project.headless_cms.model.UserStatus;
import com.project.headless_cms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWTUtil jwtUtil;

    // REGISTER
    public Users register(Users user) {

        // encode password
        user.setPassword(encoder.encode(user.getPassword()));

        // default values
        user.setRole(UserRole.EDITOR);
        user.setStatus(UserStatus.ACTIVE);
        user.setJoinedDate(LocalDate.now());

        return repo.save(user);
    }

    // LOGIN
    public String login(String email, String password) {

        Users user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}