package com.example.clothes.repositories;

import com.example.clothes.entities.Brand;
import com.example.clothes.entities.Category;
import com.example.clothes.entities.Clothes;
import com.example.clothes.entities.Size;
import com.example.clothes.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    List<Clothes> findByGender(Gender gender);
    List<Clothes>findByBrand(Brand brand);

    @Query(nativeQuery = true, value =
            "SELECT * FROM clothes where (:category_id = :category_id or :category_id is null) AND (:max_price IS NULL OR price <= :max_price)" +
            "AND (:min_price IS NULL OR price <= :min_price)")
    List<Clothes>filteredElements(@Param("category_id") Long category_id,@Param("max_price") double max_price,@Param("min_price") double min_price);
    List<Clothes>findByCategoryAndPriceBetween(Category category, double a, double b);

    List<Clothes> findBySize(Size size);
}
