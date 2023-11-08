package com.example.clothes.services.Repo;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Image;

import java.util.List;

public interface ImageService {
    void save(String link);
    public Image saveAndReturn(String link);
    Image findById(Long id);
    List<Brand> findAll();
}
