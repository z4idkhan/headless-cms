package com.project.headless_cms.controller;

import com.project.headless_cms.config.JWTUtil;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.service.AuthService;
import com.project.headless_cms.auth.LoginRequest;
import com.project.headless_cms.auth.LoginResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWTUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JWTUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @GetMapping("/test")
    public String test() {
        return "Auth API Working";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {

        if (req == null || req.getEmail() == null || req.getPassword() == null) {
            throw new RuntimeException("Invalid login request");
        }

        // ✅ Step 1: validate user
        Users user = authService.login(req.getEmail(), req.getPassword());

        // ✅ Step 2: generate JWT with role
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return authService.register(user);
    }
}