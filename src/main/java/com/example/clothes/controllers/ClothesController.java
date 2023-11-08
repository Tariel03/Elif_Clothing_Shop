package com.example.clothes.controllers;

import com.example.clothes.dto.response.ClothResponse;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Gender;
import com.example.clothes.repositories.ClothesRepository;
import com.example.clothes.services.Impl.ClothesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clothes")
public class ClothesController {
    private final ClothesServiceImpl clothesService;
    @GetMapping("/all")
    ResponseEntity<Page<ClothResponse>> findAll(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ClothResponse> clothPage = clothesService.findAll(pageable);
        return ResponseEntity.ok(clothPage);
    }
    @GetMapping("/gender")
    ResponseEntity<List<ClothResponse>>findByGender(@RequestParam("gender")Gender gender){
        return ResponseEntity.ok(clothesService.findByGender(gender));
    }
    @GetMapping("/brand")
    ResponseEntity<List<ClothResponse>>findByBrand(@RequestParam("brand") String brand){
        return ResponseEntity.ok(clothesService.findByBrand(brand));
    }

    @GetMapping("/filtered")
    List<Clothes>filtered(@RequestParam Long category_id, @RequestParam double maxPrice, @RequestParam double minPrice){
        return clothesService.filteredElements(category_id,maxPrice, minPrice);
    }




}
