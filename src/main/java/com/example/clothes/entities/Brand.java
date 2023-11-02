package com.example.clothes.entities;

import com.example.clothes.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "brand")
    @NotBlank(message = "Name of the brand can't be blank")
    String brand;
    @Enumerated(EnumType.STRING)
    Status status;
    @PrePersist
    public void prePersist() {
        status = Status.Active;
    }
}
