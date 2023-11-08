package com.example.clothes.services.Impl;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Color;
import com.example.clothes.enums.Status;
import com.example.clothes.repositories.ColorRepository;
import com.example.clothes.services.Repo.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {
    ColorRepository colorRepository;
    @Override
    public void save(String color) {
        colorRepository.save(Color.builder().color(color).build());
    }

    @Override
    public Color findById(Long id) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if(optionalColor.isPresent()){
            return optionalColor.get();
        }
        throw new RuntimeException("No element by this element");
    }

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public void changeStatusToDeleted(Long id) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if(optionalColor.isPresent()){
            Color color = optionalColor.get();
            color.setStatus(Status.DELETED);
            colorRepository.save(color);
        }
        throw new RuntimeException("No element by this element");
    }

    @Override
    public Color findByColor(String color) {
        Optional<Color> optionalColor = colorRepository.findByColor(color);
        if(optionalColor.isPresent()){
            return optionalColor.get();
        }
        throw new RuntimeException("No element by this color");
    }
}
