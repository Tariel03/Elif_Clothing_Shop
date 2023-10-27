package com.example.clothes.repositories;

import com.example.clothes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  Boolean existsByEmail(String email);

}
