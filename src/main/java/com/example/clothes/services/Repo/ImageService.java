package com.example.clothes.services.Repo;

import com.example.clothes.dto.request.BrandDto;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Image;

import java.util.List;

public interface ImageService {
    void save(String link);
    Image findById(Long id);
    List<Brand> findAll();
}
