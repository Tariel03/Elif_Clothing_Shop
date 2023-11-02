package com.example.clothes.repositories;

import com.example.clothes.entities.Brand;
import com.example.clothes.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand>findByBrand(String brand);
    List<Brand> findByStatus(Status status);
}
