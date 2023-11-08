package com.example.clothes.services.Repo;

import com.example.clothes.dto.request.ClothRequest;
import com.example.clothes.dto.response.ClothResponse;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClothService {
    List<ClothResponse> findByGender(Gender gender);
    List<ClothResponse> findByBrand(String brand);
    void save(ClothRequest clothDto);
    void save(Clothes clothes);


    Clothes findById(Long id) throws RuntimeException;
    Page<ClothResponse> findAll(Pageable pageable);
    List<Clothes>filteredElements( Long category_id,double max_price,double min_price);
}
