package com.example.clothes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tokenId;
	private String confirmationToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	public ConfirmationToken(User user) {
		this.user = user;
	}

	public ConfirmationToken(User user, String generatedString) {
		this.user = user;
		this.confirmationToken = generatedString;
	}
	
}
