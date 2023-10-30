package com.example.clothes.dto.request;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Category;
import com.example.clothes.entities.Image;
import com.example.clothes.enums.Gender;

public class ClothDto {
    String name;
    String description;
    int price;
    boolean onStock;
    Gender gender;
    Brand brand;
    Category category;


}
