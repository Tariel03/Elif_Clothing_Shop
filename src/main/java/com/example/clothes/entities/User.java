package com.example.clothes.entities;

import com.example.clothes.entities.base.BaseEntity;
import com.example.clothes.enums.Role;
import com.example.clothes.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User extends BaseEntity implements UserDetails {

    @Column(name="firstname")
    @NotBlank(message = "Firstname may not be blank")
    String firstname;

    @Column(name="lastname")
    @NotBlank(message = "Lastname may not be blank")
    String lastname;

    @Column(name="email")
    @NotNull
    @Email
    String email;

    @Column(name="password")
    String password;

    @Column(name="phone_number")
    String phoneNumber;

    @Column(name="enable")
    @Builder.Default
    boolean enable = false;

    @Column(name="role")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    Role role=Role.USER;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}