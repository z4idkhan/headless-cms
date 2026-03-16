package com.project.headless_cms.repository;

import com.project.headless_cms.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    // Used by Spring Security login
    Optional<Users> findByEmail(String email);

    // Used in Users search API
    List<Users> findByFullNameContainingIgnoreCase(String keyword);

}