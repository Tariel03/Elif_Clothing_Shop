package com.example.clothes.services.Repo;

import com.example.clothes.dto.request.BrandDto;
import com.example.clothes.entities.Brand;

import java.util.List;

public interface BrandService {
    void save(String brand);
    Brand findById(Long id);
    List<Brand> findAll();
    Brand findByBrand(String brand);



}
