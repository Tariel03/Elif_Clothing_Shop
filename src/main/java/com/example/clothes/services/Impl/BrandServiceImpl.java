package com.example.clothes.services.Impl;

import com.example.clothes.entities.Brand;
import com.example.clothes.enums.Status;
import com.example.clothes.repositories.BrandRepository;
import com.example.clothes.services.Repo.BrandService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    BrandRepository brandRepository;
    @Override
    public void save(String brand) {
        brandRepository.save(Brand.builder().brand(brand)
                .build());
    }

    @Override
    public Brand findById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if(optionalBrand.isPresent()){
            return optionalBrand.get();
        }
        throw new RuntimeException("No element by this element");
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> findActiveBrands() {
        return brandRepository.findByStatus(Status.Active);
    }

    @Override
    public Brand findByBrand(String brand) {
        Optional<Brand> optionalBrand = brandRepository.findByBrand(brand);
        if(optionalBrand.isPresent()){
            return optionalBrand.get();
        }
        throw new RuntimeException("No element by this name");
    }

    @Override
    public void changeStatusToDeleted(Long id) {
        Brand brand = findById(id);
        brand.setStatus(Status.Deleted);
        brandRepository.save(brand);
    }
}
