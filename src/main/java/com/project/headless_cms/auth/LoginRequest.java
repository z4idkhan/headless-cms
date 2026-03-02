package com.project.headless_cms.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
