package com.example.clothes.dto.response;

import com.example.clothes.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothDto {
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    @Min(value = 0, message = "The price can't be lower than 0")
    int price;
    String fabric;
    boolean onStock;

}
