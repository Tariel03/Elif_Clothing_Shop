package com.example.clothes.services.Impl;

import com.example.clothes.dto.request.ClothRequest;
import com.example.clothes.dto.response.ClothResponse;
import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Clothes;
import com.example.clothes.enums.Gender;
import com.example.clothes.mappers.ClothesMapper;
import com.example.clothes.repositories.CategoryRepository;
import com.example.clothes.repositories.ClothesRepository;
import com.example.clothes.services.Repo.CategoryService;
import com.example.clothes.services.Repo.ClothService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothesServiceImpl implements ClothService {
    private  final ClothesRepository clothesRepository;
    private final CategoryService categoryService;
    private final BrandServiceImpl brandService;
    private final ClothesMapper clothesMapper;
    @Override
    public List<ClothResponse> findByGender(Gender gender) {
        return clothesMapper.toListDto(clothesRepository.findByGender(gender));
    }

    @Override
    public List<ClothResponse> findByBrand(String brand) {
        Brand brand1 = brandService.findByBrand(brand);
        return clothesMapper.toListDto(clothesRepository.findByBrand(brand1));
    }

    @Override
    public void save(ClothRequest request) {
        Clothes clothes = clothesMapper.toClothes(request);
        clothesRepository.save(clothes);
    }

    @Override
    public void save(Clothes clothes) {
        clothesRepository.save(clothes);
    }


//    @Override
//    public void save(ClothDto dto) {
//
//    }

    @Override
    public Clothes findById(Long id) throws RuntimeException {
        Optional<Clothes> optionalClothes = clothesRepository.findById(id);
        if(optionalClothes.isPresent()){
            return optionalClothes.get();
        }
        throw  new RuntimeException("No element by this id");
    }

    @Override
    public Page<ClothResponse> findAll(Pageable pageable) {
        return clothesMapper.toPageDto(clothesRepository.findAll(pageable));
    }


    @Override
    public List<Clothes> filteredElements(Long category_id, double max_price, double min_price) {
        return clothesRepository.findByCategoryAndPriceBetween(categoryService.findById(category_id), max_price, min_price);
    }
}
