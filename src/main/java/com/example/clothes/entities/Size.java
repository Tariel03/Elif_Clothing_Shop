package com.example.clothes.entities;

import com.example.clothes.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "size",uniqueConstraints = {
        @UniqueConstraint(columnNames = "size")
})

public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "size")
    @NotBlank(message = "Name of the size can't be blank")
    String size;
    @Enumerated(EnumType.STRING)
    Status status;
    @PrePersist
    public void prePersist() {
        status = Status.Active;
    }


}
