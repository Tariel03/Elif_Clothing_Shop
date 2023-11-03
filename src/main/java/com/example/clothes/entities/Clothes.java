package com.example.clothes.entities;

import com.example.clothes.enums.Gender;
import com.example.clothes.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "clothes")
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(columnDefinition = "TEXT", name = "description")
    String description;
    @Column(name = "price")
    @Min(value = 0, message = "The price can't be lower than 0")
    int price;
    @Column(name = "fabric")
    String fabric;
    @Column(name = "onStock")
    boolean onStock;
    @Enumerated(EnumType.STRING)
    Status status;
    @Column(name = "creationTime")
    LocalTime creationTime;
    @Column(name = "creationDate")
    LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @JoinColumn(name = "brand_id")
    @ManyToOne
    Brand brand;
    @JoinColumn(name = "image_id")
    @ManyToOne
    Image image;
    @JoinColumn(name = "category_id")
    @ManyToOne
    Category  category;
    @PrePersist
    public void prePersist() {
        if (creationDate == null || creationTime == null) {
            creationDate = LocalDate.now();
            creationTime = LocalTime.now();
        }
    }

}
