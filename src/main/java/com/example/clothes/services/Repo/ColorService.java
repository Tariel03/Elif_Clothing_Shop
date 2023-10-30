package com.example.clothes.services.Repo;

import com.example.clothes.dto.request.BrandDto;
import com.example.clothes.dto.request.ColorDto;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Color;

import java.util.List;

public interface ColorService {
    void save(String color);
    Color findById(Long id);
    List<Color> findAll();
    Color findByColor(String color);
}
