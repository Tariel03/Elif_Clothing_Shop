package com.example.clothes.services.Impl;

import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Color;
import com.example.clothes.entities.Size;
import com.example.clothes.entities.Stock;
import com.example.clothes.repositories.StockRepository;
import com.example.clothes.services.Repo.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private  final StockRepository stockRepository;
    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> findBySize(Size size) {
        return stockRepository.findBySize(size);
    }

    @Override
    public List<Stock> findByColor(Color color) {
        return stockRepository.findByColor(color);
    }

    @Override
    public List<Stock> findByClothes(Clothes clothes) {
        return null;
    }

    @Override
    public Stock findById(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if(stockOptional.isPresent()){
            return stockOptional.get();
        }
        throw new RuntimeException("No element given by this id");

    }

    @Override
    public void save(Stock stock) {
        stockRepository.save(stock);

    }

    @Override
    public void save(Long size_id, Long color_id, Long clothes_id) {

    }

    @Override
    public void AddToQuantity(Long id, int quantity) {
        Stock stock = findById(id);
        int initialQuantity = stock.getQuantity();
        if(initialQuantity >= quantity){
            stock.setQuantity(initialQuantity - quantity);
            save(stock);
        }
        throw new RuntimeException("Not enough goods on the stock!");

    }
}
