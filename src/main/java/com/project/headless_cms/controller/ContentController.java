package com.project.headless_cms.controller;

import com.project.headless_cms.model.Content;
import com.project.headless_cms.model.Users;
import com.project.headless_cms.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    ContentService service;

    @PostMapping
    public Content create(@RequestBody Content c){

        return service.create(c);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return service.verify(user);
    }

    @PutMapping("/{id}/publish")
    public Content publish(
            @PathVariable Long id){

        return service.publish(id);
    }

    @GetMapping("/public")
    public List<Content> getPublic(){

        return service.publicData();
    }
}
