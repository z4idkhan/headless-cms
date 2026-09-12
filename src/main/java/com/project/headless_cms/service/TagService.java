package com.project.headless_cms.service;

import com.project.headless_cms.dto.TagRequestDTO;
import com.project.headless_cms.dto.TagResponseDTO;
import com.project.headless_cms.model.Tag;
import com.project.headless_cms.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagResponseDTO> getAllTags(String keyword) {

        List<Tag> tags;

        if (keyword == null || keyword.isBlank()) {
            tags = tagRepository.findAll();
        } else {
            tags = tagRepository.findByNameContainingIgnoreCase(keyword);
        }

        return tags.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public TagResponseDTO createTag(TagRequestDTO request) {

        Tag tag = new Tag();

        tag.setName(request.getName());

        Tag savedTag = tagRepository.save(tag);

        return convertToResponseDTO(savedTag);
    }

    public void deleteTag(Long id) {

        tagRepository.deleteById(id);
    }

    private TagResponseDTO convertToResponseDTO(Tag tag) {

        return new TagResponseDTO(
                tag.getId(),
                tag.getName(),
                tag.getSlug()
        );
    }
}