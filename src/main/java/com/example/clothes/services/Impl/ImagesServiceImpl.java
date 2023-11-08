package com.example.clothes.services.Impl;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Image;
import com.example.clothes.repositories.ImageRepository;
import com.example.clothes.services.Repo.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Override
    public void save(String link) {
        imageRepository.save(Image.builder().imageLink(link).build());
    }
    public Image saveAndReturn(String link) {
        return imageRepository.save(Image.builder().imageLink(link).build());
    }
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image findById(Long id) {
        return null;
    }

    @Override
    public List<Brand> findAll() {
        return null;
    }
}
