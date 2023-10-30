package com.example.clothes.repositories;

import com.example.clothes.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color>findByColor(String color);
}
