package com.example.clothes.repositories.auth;

import com.example.clothes.entities.auth.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

  @Query(value = """
      select t from AccessToken t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<AccessToken> findAllValidTokenByUser(Long id);

  Optional<AccessToken> findByToken(String token);

}