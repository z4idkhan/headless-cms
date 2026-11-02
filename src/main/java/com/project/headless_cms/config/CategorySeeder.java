package com.project.headless_cms.config;

import com.project.headless_cms.model.Category;
import com.project.headless_cms.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// Adds default categories on startup — only if the table is empty
@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        if (categoryRepository.count() > 0) {
            return; // already have categories, do nothing
        }

        List<String> names = List.of(
                "Technology",
                "Documentation",
                "News",
                "Design",
                "HR",
                "Product"
        );

        for (String name : names) {
            Category category = new Category();
            category.setName(name);
            categoryRepository.save(category);
        }

        System.out.println("✅ Default categories added");
    }
}