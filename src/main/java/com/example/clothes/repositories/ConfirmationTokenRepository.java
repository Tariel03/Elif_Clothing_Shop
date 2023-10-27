package com.example.clothes.repositories;

import com.example.clothes.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	
	ConfirmationToken findByConfirmationToken(String confirmToken);

	@Transactional
	@Modifying(clearAutomatically=true, flushAutomatically=true)
	void deleteByUserId(Long userId);

}
