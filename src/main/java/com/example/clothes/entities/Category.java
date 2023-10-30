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
@Table(name = "category",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "type")
        })
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "type" )
    @NotBlank(message = "Type of the cloth can't be blank")
    String type;

}
