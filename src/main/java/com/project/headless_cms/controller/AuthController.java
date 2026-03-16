package com.project.headless_cms.controller;

import com.project.headless_cms.config.JWTUtil;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.service.AuthService;
import com.project.headless_cms.auth.LoginRequest;
import com.project.headless_cms.auth.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test() {
        return "Auth API Working";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {

        if (req == null ||
                req.getEmail() == null ||
                req.getPassword() == null) {

            throw new RuntimeException("Invalid login request");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        String token = jwtUtil.generateToken(req.getEmail());

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return authService.register(user);
    }
}