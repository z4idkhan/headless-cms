package com.project.headless_cms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = " USERNAME ")
    private String username;

    @Column(name = " EMAIL ")
    private String email;

    @Column(name = " PASSWORD ")
    private String password;

    @Column(name = " ROLE ")
    private String role;

}
