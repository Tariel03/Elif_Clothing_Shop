package com.example.clothes.services.Repo;

import com.example.clothes.entities.Brand;

import java.util.List;

public interface BrandService {
    void save(String brand);
    Brand findById(Long id);
    List<Brand> findAll();
    List<Brand>findActiveBrands();
    Brand findByBrand(String brand);
    void changeStatusToDeleted(Long id);



}
