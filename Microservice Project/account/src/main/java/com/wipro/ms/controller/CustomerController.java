package com.wipro.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ms.dto.AccountsContactInfoDto;
import com.wipro.ms.dto.CustomerDetailsDto;
import com.wipro.ms.service.CustomerServiceDetailsImpl;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerServiceDetailsImpl customerServiceDetailsImpl;
	
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam String MobileNumber){
		CustomerDetailsDto customerDetailsDto=customerServiceDetailsImpl.fetchCustomerDetails(MobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
	}
	
	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;
	
	@GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
    	return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }
	
}
