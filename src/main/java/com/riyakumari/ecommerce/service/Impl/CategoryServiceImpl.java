package com.riyakumari.ecommerce.service.Impl;

import com.riyakumari.ecommerce.entity.Category;
import com.riyakumari.ecommerce.repositories.CategoryRepository;
import com.riyakumari.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categoryList = categoryRepository.findAll();
        Category category = categoryList.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));

        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categoryList = categoryRepository.findAll();
        Optional<Category> optionalCategory = categoryList.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepository.save(existingCategory);
            return savedCategory;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
        }
    }
}
