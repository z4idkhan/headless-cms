package com.project.headless_cms.service;

import com.project.headless_cms.model.Users;
import com.project.headless_cms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Users user = repo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
