package com.example.clothes.services.Repo;

import com.example.clothes.dto.request.BrandDto;
import com.example.clothes.dto.request.SizeDto;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Size;

import java.util.List;

public interface SizeService {
    void save(String size);
    Size findById(Long id);
    List<Size> findAll();
    Size findBySize(String size);
}
