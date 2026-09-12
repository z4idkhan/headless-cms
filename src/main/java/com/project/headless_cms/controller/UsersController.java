package com.project.headless_cms.controller;

import com.project.headless_cms.dto.UserRequestDTO;
import com.project.headless_cms.dto.UserResponseDTO;
import com.project.headless_cms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<UserResponseDTO> getUsers(
            @RequestParam(required = false) String keyword) {

        return usersService.getAllUsers(keyword);
    }

    @PostMapping
    public UserResponseDTO createUser(
            @RequestBody UserRequestDTO request) {

        return usersService.createUser(request);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO request) {

        return usersService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        usersService.deleteUser(id);

        return "User deleted successfully";
    }
}