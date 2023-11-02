package com.example.clothes.services.Repo;

import com.example.clothes.dto.response.ClothDto;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Gender;

import java.util.List;

public interface ClothService {
    List<Clothes> findByGender(Gender gender);
    List<Clothes> findByBrand(String brand);
    void save(ClothDto clothDto);

    Clothes findById(Long id) throws RuntimeException;
    List<Clothes> findAll();
    List<Clothes>filteredElements( Long category_id,double max_price,double min_price);
}
