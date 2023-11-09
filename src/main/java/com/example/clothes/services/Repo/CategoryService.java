package com.example.clothes.services.Repo;

import com.example.clothes.entities.Category;

import java.util.List;

public interface CategoryService {
    void save(String category);
    Category findById(Long id);
    List<Category> findAll();
    Category findByCategory(String category);

}
