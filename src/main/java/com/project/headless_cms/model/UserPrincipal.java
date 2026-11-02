package com.project.headless_cms.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final Users user;

    public UserPrincipal(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 🔥 SAFE CHECK (no crash, but logs issue)
        if (user.getRole() == null) {
            System.out.println("❌ User role is NULL for user: " + user.getEmail());
            return List.of(); // no role → will cause 403 (correct behavior)
        }

        String role = user.getRole().name();

        System.out.println("✅ Assigning authority: ROLE_" + role);

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // email used as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        if (user.getStatus() == null) {
            return true;
        }

        return user.getStatus().name().equals("ACTIVE");
    }

    // Optional helpers
    public Long getId() {
        return user.getId();
    }

    public String getFullName() {
        return user.getFullName();
    }
}