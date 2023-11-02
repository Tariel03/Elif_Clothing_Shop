package com.example.clothes.mappers;

import com.example.clothes.dto.response.ClothDto;
import com.example.clothes.entities.Clothes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper
public interface ClothesMapper {
    ClothesMapper Instance = Mappers.getMapper(ClothesMapper.class);
    ClothDto toClothDto(Clothes cloth);
    Clothes toClothes(ClothDto clothDto);
}
