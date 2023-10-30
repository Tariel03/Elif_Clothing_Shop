package com.example.clothes.controllers;

import com.example.clothes.entities.Brand;
import com.example.clothes.services.Repo.BrandService;
import com.example.clothes.services.Repo.ColorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final BrandService brandService;
    private final ColorService colorService;
    @PostMapping("/create/clothes")
    public ResponseEntity<String> createClothes() {
        return null;
    }

    @PostMapping("/create/brand")
    public ResponseEntity<String> createBrand(String brand) {
        brandService.save(brand);
        return ResponseEntity.ok("Successfully created!");
    }
    @PostMapping("/create/color")
    public ResponseEntity<String>createColor(String color){
        colorService.save(color);
        return ResponseEntity.ok("Successfully created!");
    }
}




