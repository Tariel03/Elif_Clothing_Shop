package com.example.clothes.repositories;

import com.example.clothes.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   Optional<Category> findByType(String category);

}
