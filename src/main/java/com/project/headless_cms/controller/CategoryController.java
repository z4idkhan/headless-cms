package com.project.headless_cms.controller;

import com.project.headless_cms.dto.CategoryRequestDTO;
import com.project.headless_cms.dto.CategoryResponseDTO;
import com.project.headless_cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDTO> getCategories(
            @RequestParam(required = false) String keyword) {

        return categoryService.getAllCategories(keyword);
    }

    @PostMapping
    public CategoryResponseDTO createCategory(
            @RequestBody CategoryRequestDTO categoryRequest) {

        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO categoryRequest) {

        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "Category deleted successfully";
    }
}