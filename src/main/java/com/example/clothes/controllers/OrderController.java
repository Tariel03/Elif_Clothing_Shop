package com.example.clothes.controllers;

import com.example.clothes.entities.Stock;
import com.example.clothes.services.CurrentUserService;
import com.example.clothes.services.Impl.OrderServiceImpl;
import com.example.clothes.services.Impl.StockServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderServiceImpl orderService;
    private final StockServiceImpl stockService;
    @PostMapping
    public void order(@RequestParam Long stock_id){
        Stock stock = stockService.findById(stock_id);
        orderService.save(stock_id);
    }


}
