package com.wipro.ms.dto;

import lombok.Data;

@Data
public class CustomerDetailsDto {

	private CustomerAccountDetails accountDetails;
	private CardsDto cardsDto;
	private LoansDto loansDto;
}
