package com.project.headless_cms.service;

import com.project.headless_cms.model.UserRole;
import com.project.headless_cms.model.UserStatus;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return userRepository.findAll();
        }
        return userRepository.findByFullNameContainingIgnoreCase(keyword);
    }

    public Users createUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setJoinedDate(LocalDate.now());

        if (user.getRole() == null) {
            user.setRole(UserRole.EDITOR);
        }

        if (user.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        }

        return userRepository.save(user);
    }

    public Users updateUser(Long id, Users request) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}