package com.project.headless_cms.config;

import com.project.headless_cms.model.Tag;
import com.project.headless_cms.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// Adds default tags on startup — only if the table is empty
@Component
public class TagSeeder implements CommandLineRunner {

    private final TagRepository tagRepository;

    public TagSeeder(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) {

        if (tagRepository.count() > 0) {
            return; // already have tags, do nothing
        }

        List<String> names = List.of(
                "React",
                "TypeScript",
                "API",
                "Security",
                "UI/UX",
                "DevOps",
                "Docker",
                "PostgreSQL",
                "Testing"
        );

        for (String name : names) {
            Tag tag = new Tag();
            tag.setName(name);
            tagRepository.save(tag);
        }

        System.out.println("✅ Default tags added");
    }
}