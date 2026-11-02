package com.project.headless_cms.dto;

import com.project.headless_cms.model.UserRole;
import com.project.headless_cms.model.UserStatus;

import java.time.LocalDate;

public class UserResponseDTO {

    private Long id;

    private String fullName;

    private String email;

    private UserRole role;

    private UserStatus status;

    private LocalDate joinedDate;

    public UserResponseDTO(
            Long id,
            String fullName,
            String email,
            UserRole role,
            UserStatus status,
            LocalDate joinedDate
    ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.joinedDate = joinedDate;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }
}