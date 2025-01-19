package com.wipro.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {
	
	@NotEmpty(message="Mobile Number can not be a null or empty")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
	private String mobileNumber;
	@NotEmpty(message="Card Type can not be a null or empty")
	private String cardType; 
//	@NotEmpty(message="Card Number can not be a null or empty")
	private String cardNumber;
	@Positive(message = "Total card limit should be greater than zero")
	private Long TotalLimit;
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	private Long amountUsed;
//	@PositiveOrZero(message = "Total available amount should be equal or greater than zero")
	private Long available_amount;
}
