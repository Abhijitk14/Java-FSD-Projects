package com.wipro.ms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.ms.dao.CustomerDao;
import com.wipro.ms.dto.CustomerAccountDetails;
import com.wipro.ms.entity.Customer;

@Service
public class CustomerServices {

	@Autowired
	CustomerDao customerDao;
	
	public Customer createAccount(Customer cutomer) {
		
		return customerDao.createAccount(cutomer);
	}

	public Optional<CustomerAccountDetails> getAccountDetails(String mobileNumber) {
		return customerDao.getAccountDetails(mobileNumber);
	}
	
	public Boolean deleteCustomerAccount(String mobileNumber) {
		return customerDao.deleteCustomerAccount(mobileNumber);
	}
	
	public Optional<CustomerAccountDetails> updateCustomerAccount(Long AccountNumber,CustomerAccountDetails customerAccount){
		return customerDao.updateCustomerAccount(AccountNumber, customerAccount);
	}
}
