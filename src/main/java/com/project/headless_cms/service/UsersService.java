package com.project.headless_cms.service;

import com.project.headless_cms.dto.UserRequestDTO;
import com.project.headless_cms.dto.UserResponseDTO;
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


    public List<UserResponseDTO> getAllUsers(String keyword) {

        List<Users> users;

        if (keyword == null || keyword.isBlank()) {
            users = userRepository.findAll();
        } else {
            users = userRepository
                    .findByFullNameContainingIgnoreCase(keyword);
        }

        return users.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }


    public UserResponseDTO createUser(UserRequestDTO request) {

        Users user = new Users();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setJoinedDate(LocalDate.now());


        if (request.getRole() == null) {
            user.setRole(UserRole.EDITOR);
        } else {
            user.setRole(request.getRole());
        }


        if (request.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        } else {
            user.setStatus(request.getStatus());
        }


        Users savedUser = userRepository.save(user);

        return convertToResponseDTO(savedUser);
    }


    public UserResponseDTO updateUser(
            Long id,
            UserRequestDTO request) {

        Users user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());


        Users updatedUser = userRepository.save(user);

        return convertToResponseDTO(updatedUser);
    }


    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }


    private UserResponseDTO convertToResponseDTO(Users user) {

        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getJoinedDate()
        );
    }
}