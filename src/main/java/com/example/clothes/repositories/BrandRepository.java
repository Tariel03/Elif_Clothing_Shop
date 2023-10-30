package com.example.clothes.repositories;

import com.example.clothes.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand>findByBrand(String brand);
}
