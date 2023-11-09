package com.example.clothes.services.Impl;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Size;
import com.example.clothes.repositories.SizeRepository;
import com.example.clothes.services.Repo.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {
    SizeRepository sizeRepository;
    @Override
    public void save(String size) {
        sizeRepository.save(Size.builder().size(size).build());
    }

    @Override
    public Size findById(Long id) {
        Optional<Size> optionalSize = sizeRepository.findById(id);
        if(optionalSize.isPresent()){
            return optionalSize.get();
        }
        throw new RuntimeException("No element by this element");
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findBySize(String size) {
        Optional<Size> optionalSize = sizeRepository.findBySize(size);
        if(optionalSize.isPresent()){
            return optionalSize.get();
        }
        throw new RuntimeException("No element by this element");
    }
}
