package com.wipro.ms.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wipro.ms.dto.CardsDto;
import com.wipro.ms.dto.CustomerAccountDetails;
import com.wipro.ms.dto.CustomerDetailsDto;
import com.wipro.ms.dto.LoansDto;
import com.wipro.ms.entity.Account;
import com.wipro.ms.entity.Customer;
import com.wipro.ms.repository.AccountRepository;
import com.wipro.ms.repository.CustomerRepository;
import com.wipro.ms.service.client.CardsFeignClient;
import com.wipro.ms.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceDetailsImpl {

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;
	
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		Optional<Customer> optCustomer=customerRepository.findBymobileNumber(mobileNumber);
		if(optCustomer.isPresent()) {
			Optional<Account> optAccount=accountRepository.findById(optCustomer.get().getCustomer_id());
			if(optAccount.isPresent()) {
				CustomerDetailsDto customerDetailsDto=new CustomerDetailsDto();
				customerDetailsDto.setAccountDetails(new CustomerAccountDetails(optCustomer.get(), optAccount.get()));
				
				ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
		        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
		        
		        ResponseEntity<CardsDto> cardsDtoResponseEntity= cardsFeignClient.getCards(mobileNumber);
		        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
		        
		        return customerDetailsDto;
			}

		}
		return null;
	}
}
