package com.example.clothes.services.Repo;

import com.example.clothes.entities.Size;

import java.util.List;

public interface SizeService {
    void save(String size);
    Size findById(Long id);
    List<Size> findAll();
    Size findBySize(String size);
}
