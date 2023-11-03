package com.example.clothes.entities;

import com.example.clothes.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="token", unique = true)
    String token;

    @Column(name="token_type")
    @Enumerated(EnumType.STRING)
    TokenType tokenType = TokenType.BEARER;

    @Column(name="revoked")
    boolean revoked;

    @Column(name="expired")
    boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
