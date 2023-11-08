package com.example.clothes.mappers;

import com.example.clothes.dto.request.ClothRequest;
import com.example.clothes.dto.response.ClothResponse;
import com.example.clothes.entities.Clothes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface ClothesMapper {
    ClothesMapper Instance = Mappers.getMapper(ClothesMapper.class);
    ClothResponse toClothResponse(Clothes cloth);
    Clothes toClothes(ClothResponse clothDto);
    Clothes toClothes(ClothRequest clothDto);

    ClothRequest toClothRequest(Clothes clothes);
    List<ClothResponse> toListDto(List<Clothes> list);
    default Page<ClothResponse> toPageDto(Page<Clothes> page) {
        return page.map(this::toClothResponse);
    }

}
