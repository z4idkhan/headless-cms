package com.project.headless_cms.controller;

import com.project.headless_cms.dto.TagRequestDTO;
import com.project.headless_cms.dto.TagResponseDTO;
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
    public List<TagResponseDTO> getTags(
            @RequestParam(required = false) String keyword) {

        return tagService.getAllTags(keyword);
    }

    @PostMapping
    public TagResponseDTO createTag(
            @RequestBody TagRequestDTO request) {

        return tagService.createTag(request);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {

        tagService.deleteTag(id);

        return "Tag deleted successfully";
    }
}