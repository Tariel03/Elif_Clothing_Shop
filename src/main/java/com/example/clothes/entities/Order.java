package com.example.clothes.entities;

import com.example.clothes.entities.base.BaseEntity;
import com.example.clothes.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_order")
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    Stock stock;
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;
    @Enumerated(value = EnumType.STRING)
    OrderStatus orderStatus;
    @PostPersist()
    public void postPersist(){
        orderStatus = OrderStatus.ACTIVE;
    }



}
