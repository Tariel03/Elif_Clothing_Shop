package com.example.clothes.repositories;

import com.example.clothes.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Long> {
    Optional<Size>findBySize(String size);
}
