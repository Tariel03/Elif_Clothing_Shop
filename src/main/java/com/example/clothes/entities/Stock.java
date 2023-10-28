package com.example.clothes.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "stock")
/* This class is many-to-many relationship table or class, alternatively could be called Color_size_cloth.
 * But I decided that name wouldn't be very suiting name and honestly too long
 */
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
     @JoinColumn(name = "color_id")
    Color color;
    @ManyToOne
    @JoinColumn(name = "size_id")
    Size size;
    @ManyToOne
    @JoinColumn(name = "clothes_id")
    Clothes clothes;
    @Column(name = "quantity")
    @Min(value = 0, message = "The quantity of the product on stock can't be less 0")
    int quantity;


}
