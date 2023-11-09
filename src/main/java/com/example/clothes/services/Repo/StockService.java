package com.example.clothes.services.Repo;

import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Color;
import com.example.clothes.entities.Size;
import com.example.clothes.entities.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<Stock> findAll();
    List<Stock>findBySize(Size size);
    List<Stock>findByColor(Color color);
    List<Stock>findByClothes(Clothes clothes);
    Stock findById(Long id);
    void save(Stock stock);
    void save(Long size_id, Long color_id, Long clothes_id);
    void AddToQuantity(Long id, int quantity);


}
