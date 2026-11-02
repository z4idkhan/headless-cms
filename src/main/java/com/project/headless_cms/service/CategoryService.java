package com.project.headless_cms.service;

import com.project.headless_cms.dto.CategoryRequestDTO;
import com.project.headless_cms.dto.CategoryResponseDTO;
import com.project.headless_cms.model.Category;
import com.project.headless_cms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<CategoryResponseDTO> getAllCategories(String keyword) {

        List<Category> categories;

        if (keyword == null || keyword.isBlank()) {
            categories = categoryRepository.findAll();
        } else {
            categories = categoryRepository.findByNameContainingIgnoreCase(keyword);
        }

        return categories.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }


    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);

        return convertToResponseDTO(savedCategory);
    }


    public CategoryResponseDTO updateCategory(
            Long id,
            CategoryRequestDTO request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return convertToResponseDTO(updatedCategory);
    }


    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }


    private CategoryResponseDTO convertToResponseDTO(Category category) {

        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription()
        );
    }
}