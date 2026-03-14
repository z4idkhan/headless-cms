package com.project.headless_cms.service;

import com.project.headless_cms.model.Tag;
import com.project.headless_cms.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return tagRepository.findAll();
        }
        return tagRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}