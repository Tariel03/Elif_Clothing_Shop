package com.example.clothes.entities.base;

import com.example.clothes.utils.DateUtil;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "updated_date")
    LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    LocalDateTime deletedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now(DateUtil.getZoneId());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now(DateUtil.getZoneId());
    }
}