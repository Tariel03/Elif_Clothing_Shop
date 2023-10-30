package com.example.clothes.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "color", uniqueConstraints = {
        @UniqueConstraint(columnNames = "color")
})
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "color", nullable = false)
    @NotBlank(message = "Name of the color can't be blank")
    String color;
}
