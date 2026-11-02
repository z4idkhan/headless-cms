package com.project.headless_cms.controller;

import com.project.headless_cms.config.OwnershipGuard;
import com.project.headless_cms.dto.TagRequestDTO;
import com.project.headless_cms.dto.TagResponseDTO;
import com.project.headless_cms.model.Tag;
import com.project.headless_cms.repository.TagRepository;
import com.project.headless_cms.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private OwnershipGuard guard;

    @GetMapping
    public List<TagResponseDTO> getTags(
            @RequestParam(required = false) String keyword) {

        return tagService.getAllTags(keyword);
    }

    @PostMapping
    public TagResponseDTO createTag(
            @RequestBody TagRequestDTO request) {

        TagResponseDTO created = tagService.createTag(request);

        // ✅ Save who created it
        tagRepository.findById(created.getId()).ifPresent(t -> {
            t.setCreatedBy(guard.currentUser());
            tagRepository.save(t);
        });

        return created;
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tag not found"));

        // ✅ Only creator or admin
        guard.checkOwnerOrAdmin(tag.getCreatedBy());

        tagService.deleteTag(id);

        return "Tag deleted successfully";
    }
}