package com.example.clothes.services.Repo;

import com.example.clothes.dto.request.BrandDto;
import com.example.clothes.dto.request.CategoryDto;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Category;

import java.util.List;
import java.util.concurrent.CancellationException;

public interface CategoryService {
    void save(String category);
    Category findById(Long id);
    List<Category> findAll();
    Category findByCategory(String brand);

}
