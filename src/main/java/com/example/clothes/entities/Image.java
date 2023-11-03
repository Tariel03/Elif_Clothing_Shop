package com.example.clothes.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "imageLink", nullable = true)
    String imageLink;
}
