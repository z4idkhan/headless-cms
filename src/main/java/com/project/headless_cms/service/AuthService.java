package com.project.headless_cms.service;

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

    // REGISTER
    public Users register(Users user) {
        // encode password
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // LOGIN
    public Users login(String email, String password) {
        Users user = repo.findByEmail(email);
        if (user == null) {
            user = repo.findByEmail(email);
        }

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user; // login successful
    }
}