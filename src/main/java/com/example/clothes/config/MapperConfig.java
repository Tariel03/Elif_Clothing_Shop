package com.example.clothes.config;

import com.example.clothes.mappers.ClothesMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ClothesMapper clothesMapper() {
        return Mappers.getMapper(ClothesMapper.class);
    }
}

