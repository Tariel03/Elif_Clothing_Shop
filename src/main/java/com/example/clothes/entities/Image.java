package com.example.clothes.entities;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

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
    @ManyToOne
    @JoinColumn(name = "clothes_id")
    @JsonIgnore
    private Clothes clothes;
}
