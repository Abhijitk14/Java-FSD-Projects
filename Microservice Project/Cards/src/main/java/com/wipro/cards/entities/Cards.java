package com.wipro.cards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Card_id")
	private Integer CardId; 
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="card_number")
	private String cardNumber;
	@Column(name="card_type")
	private String cardType; 
	@Column(name="total_limit")
	private Long TotalLimit;
	@Column(name="amount_used")
	private Long amountUsed;
	@Column(name="available_amount")
	private Long available_amount;
	
}
