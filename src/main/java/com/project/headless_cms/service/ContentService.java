package com.project.headless_cms.service;

import com.project.headless_cms.config.JWTUtil;
import com.project.headless_cms.model.Content;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository repo;

    @Autowired
    private JWTUtil jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
                        );

        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "Fail";
    }

    public Content create(Content c){
        c.setPublished(false);
        return repo.save(c);
    }

    public Content publish(Long id){

        Content c = repo.findById(id).orElseThrow();
        c.setPublished(true);
        return repo.save(c);
    }

    public List<Content> publicData(){
        return repo.findByPublishedTrue();
    }
}
