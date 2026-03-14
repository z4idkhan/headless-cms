package com.project.headless_cms.controller;

import com.project.headless_cms.model.Tag;
import com.project.headless_cms.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getTags(@RequestParam(required = false) String keyword) {
        return tagService.getAllTags(keyword);
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "Tag deleted successfully";
    }
}