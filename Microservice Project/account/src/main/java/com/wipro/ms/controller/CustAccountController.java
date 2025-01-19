package com.wipro.ms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ms.dto.CustomerAccountDetails;
import com.wipro.ms.entity.Customer;
import com.wipro.ms.exception.CustomerNotFoundException;
import com.wipro.ms.service.CustomerServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustAccountController {
	
	@Autowired
	CustomerServices customerServices;

	@PostMapping("/create")
	public ResponseEntity<?> createAccount(@Valid @RequestBody Customer customer) {
		Customer cust=customerServices.createAccount(customer);
		if(cust!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Customer save successfully...");
		}else {
			return null;
		}
		
	}
	
	@GetMapping("/get/{MobileNumber}")
	public ResponseEntity<?> getAccountDetails(@PathVariable("MobileNumber") String mobileNumber) {
		Optional<CustomerAccountDetails> customerAccount =customerServices.getAccountDetails(mobileNumber);
		if(customerAccount.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(customerAccount);
		}else {
			throw new CustomerNotFoundException("customer with mobileNumber " + mobileNumber + " not found");
		}
		
	}
	
	@DeleteMapping("/delete/{MobileNumber}")
	public ResponseEntity<?> deleteCustomerAccount(@PathVariable("MobileNumber") String MobileNumber){
		Boolean status=customerServices.deleteCustomerAccount(MobileNumber);
		
		if(status) {
			return ResponseEntity.status(HttpStatus.OK).body("Customer delete succesfully..");
		}else {
			throw new CustomerNotFoundException("customer with mobileNumber " + MobileNumber + " not found");
		}
		
	}
	
	@PutMapping("/update/{AccountNumber}")
	public ResponseEntity<?> updateCustomerAccount(@PathVariable("AccountNumber") Long AccountNumber, @RequestBody CustomerAccountDetails customerAccount){
//		Optional<CustomerAccountDetails> customerAccount =customerServices.updateCustomerAccount(AccountNumber, customerAccount);
		if(customerAccount!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(customerAccount);
		}else{
			throw new CustomerNotFoundException("customer with mobileNumber " + AccountNumber + " not found");
		}
	}
}
