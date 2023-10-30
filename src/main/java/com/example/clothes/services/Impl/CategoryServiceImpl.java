package com.example.clothes.services.Impl;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Category;
import com.example.clothes.repositories.CategoryRepository;
import com.example.clothes.services.Repo.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    @Override
    public void save(String category) {
        categoryRepository.save(Category.builder().type(category).build());
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new RuntimeException("No element by this id");
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCategory(String category) {
        Optional<Category> optionalCategory = categoryRepository.findByType(category);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new RuntimeException("No element by this category");
    }
}
