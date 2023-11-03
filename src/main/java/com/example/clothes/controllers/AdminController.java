package com.example.clothes.controllers;

import com.example.clothes.dto.response.ClothDto;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Status;
import com.example.clothes.services.Impl.ClothesServiceImpl;
import com.example.clothes.services.Repo.BrandService;
import com.example.clothes.services.Repo.ColorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final BrandService brandService;
    private final ColorService colorService;
    private final ClothesServiceImpl clothesService;
    @PostMapping("/create/clothes")
    public ResponseEntity<String> createClothes(@RequestBody ClothDto clothDto) {
       clothesService.save(clothDto);
       return ResponseEntity.ok("Created new instance of clothes");
    }
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>>findBrands(){
        return ResponseEntity.ok(brandService.findActiveBrands());
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




