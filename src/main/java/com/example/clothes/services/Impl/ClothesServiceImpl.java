package com.example.clothes.services.Impl;

import com.example.clothes.dto.request.ClothDto;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Gender;
import com.example.clothes.repositories.CategoryRepository;
import com.example.clothes.repositories.ClothesRepository;
import com.example.clothes.services.Repo.CategoryService;
import com.example.clothes.services.Repo.ClothService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClothesServiceImpl implements ClothService {
    ClothesRepository clothesRepository;
    CategoryService categoryService;
    @Override
    public List<Clothes> findByGender(Gender gender) {
        return clothesRepository.findByGender(gender);
    }


    @Override
    public void save(ClothDto dto) {

    }

    @Override
    public Clothes findById(Long id) throws RuntimeException {
        Optional<Clothes> optionalClothes = clothesRepository.findById(id);
        if(optionalClothes.isPresent()){
            return optionalClothes.get();
        }
        throw  new RuntimeException("No element by this id");

    }

    @Override
    public List<Clothes> findAll() {
        return clothesRepository.findAll();
    }

    @Override
    public List<Clothes> filteredElements(Long category_id, double max_price, double min_price) {
        return clothesRepository.findByCategoryAndPriceBetween(categoryService.findById(category_id), max_price, min_price);
    }
}
