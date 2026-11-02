package com.project.headless_cms.controller;

import com.project.headless_cms.config.OwnershipGuard;
import com.project.headless_cms.dto.CategoryRequestDTO;
import com.project.headless_cms.dto.CategoryResponseDTO;
import com.project.headless_cms.model.Category;
import com.project.headless_cms.repository.CategoryRepository;
import com.project.headless_cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OwnershipGuard guard;

    @GetMapping
    public List<CategoryResponseDTO> getCategories(
            @RequestParam(required = false) String keyword) {

        return categoryService.getAllCategories(keyword);
    }

    @PostMapping
    public CategoryResponseDTO createCategory(
            @RequestBody CategoryRequestDTO categoryRequest) {

        CategoryResponseDTO created =
                categoryService.createCategory(categoryRequest);

        // ✅ Save who created it
        categoryRepository.findById(created.getId()).ifPresent(c -> {
            c.setCreatedBy(guard.currentUser());
            categoryRepository.save(c);
        });

        return created;
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO categoryRequest) {

        // ✅ Only creator or admin
        guard.checkOwnerOrAdmin(findCategory(id).getCreatedBy());

        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {

        // ✅ Only creator or admin
        guard.checkOwnerOrAdmin(findCategory(id).getCreatedBy());

        categoryService.deleteCategory(id);

        return "Category deleted successfully";
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category not found"));
    }
}