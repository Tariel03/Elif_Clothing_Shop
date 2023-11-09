package com.example.clothes.services.Impl;

import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Color;
import com.example.clothes.entities.Size;
import com.example.clothes.entities.Stock;
import com.example.clothes.repositories.StockRepository;
import com.example.clothes.services.Repo.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private  final StockRepository stockRepository;
    private final ClothesServiceImpl clothesService;
    private final ColorServiceImpl colorService;
    private final SizeServiceImpl sizeService;
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
        return stockRepository.findByClothes(clothes);
    }

    @Override
    public Stock findByClothesAndColorAndSize(Long clothes_id, Long color_id, Long size_id) {
        Clothes clothes = clothesService.findById(clothes_id);
        Color color = colorService.findById(clothes_id);
        Size size = sizeService.findById(size_id);
        Optional<Stock> optionalStock = stockRepository.findByClothesAndColorAndSize(clothes,color,size);
        if(optionalStock.isPresent()){
            return optionalStock.get();
        }
        throw new RuntimeException("No data in Stock!");
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
        Clothes clothes = clothesService.findById(clothes_id);
        Color color = colorService.findById(clothes_id);
        Size size = sizeService.findById(size_id);
        Stock stock = Stock.builder().clothes(clothes).color(color).size(size).build();
        save(stock);
    }

    @Override
    public void AddToQuantity(Long id, int quantity) {
        Stock stock = findById(id);
        stock.setQuantity(stock.getQuantity()+quantity);
        save(stock);
    }

    @Override
    public void SubtractFromQuantity(Long id, int quantity) {
        Stock stock = findById(id);
        int initialQuantity = stock.getQuantity();
        if(initialQuantity >= quantity){
            stock.setQuantity(initialQuantity - quantity);
            save(stock);
        }
        throw new RuntimeException("Not enough goods on the stock!");
    }
    public List<Color>getByClothes(Long clothes_id){
        Clothes clothes = clothesService.findById(clothes_id);
        List<Color>colorList = new ArrayList<>();
        List<Stock> stockList = stockRepository.findByClothes(clothes);
        for (Stock s :
                stockList) {
            colorList.add(s.getColor());

        }
        return colorList;

    }


}
