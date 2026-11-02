package com.project.headless_cms.config;

import com.project.headless_cms.model.UserRole;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

// Helper: who is logged in, and are they allowed to change something?
@Component
public class OwnershipGuard {

    private final UserRepository userRepository;

    public OwnershipGuard(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Logged-in user (email comes from the JWT)
    public Users currentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "User not found"));
    }

    // Allow only the owner or an ADMIN — otherwise 403
    public void checkOwnerOrAdmin(Users owner) {
        Users me = currentUser();

        boolean isAdmin = me.getRole() == UserRole.ADMIN;
        boolean isOwner = owner != null && owner.getId().equals(me.getId());

        if (!isAdmin && !isOwner) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Not allowed");
        }
    }
}