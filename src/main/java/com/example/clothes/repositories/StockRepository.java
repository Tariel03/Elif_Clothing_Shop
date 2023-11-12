package com.example.clothes.repositories;

import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Color;
import com.example.clothes.entities.Size;
import com.example.clothes.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock>findBySize(Size size);
    List<Stock>findByColor(Color color);
    List<Stock>findByClothes(Clothes clothes);
    List<Stock>findByClothesAndColor(Clothes clothes, Color color);
    Optional<Stock> findByClothesAndColorAndSize(Clothes clothes, Color color, Size size);


}
