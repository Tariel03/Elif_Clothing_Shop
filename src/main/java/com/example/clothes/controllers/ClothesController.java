package com.example.clothes.controllers;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Gender;
import com.example.clothes.repositories.ClothesRepository;
import com.example.clothes.services.Impl.ClothesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clothes")
public class ClothesController {
    private final ClothesServiceImpl clothesService;
    @GetMapping("/all")
    List<Clothes> findAll(){
        return clothesService.findAll();
    }
    @GetMapping("/gender")
    List<Clothes>findByGender(@RequestParam("gender")Gender gender){
        return clothesService.findByGender(gender);
    }
    @GetMapping("/brand")
    List<Clothes>findByBrand(@RequestParam("brand") String brand){
        return clothesService.findByBrand(brand);
    }

    @GetMapping("/filtered")
    List<Clothes>filtered(@RequestParam Long category_id, @RequestParam double maxPrice, @RequestParam double minPrice){
        return clothesService.filteredElements(category_id,maxPrice, minPrice);
    }


}
