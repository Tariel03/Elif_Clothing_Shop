package com.example.clothes.dto.request;

import com.example.clothes.enums.Gender;
import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ClothRequest {
    @NotBlank
    String name;
    @NotBlank
    String description;
    @Min(value = 0, message = "The price can't be lower than 0")
    int price;
    @NotBlank
    String fabric;
    Gender gender;
}
