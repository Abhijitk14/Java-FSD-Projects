package com.wipro.ms.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wipro.ms.dto.CustomerAccountDetails;
import com.wipro.ms.entity.Account;
import com.wipro.ms.entity.Customer;
import com.wipro.ms.repository.AccountRepository;
import com.wipro.ms.repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountRepository accountRepository;
	
	public Customer createAccount(Customer customer) {
//		customer.getAccount().setCustomer_id(customer.getCustomer_id());
//		accountRepository.save(customer.getAccount());
		customerRepository.save(customer);
		Integer customer_id=customer.getCustomer_id();
		Account account =new Account();
		account.setCustomer_id(customer_id);
		accountRepository.save(account);
		
		return customer;
	}
	
//	public Optional<?> getAccountDetails(Long mobileNumber) {
//		Optional<Customer> customer=customerRepository.findByMobileNumber(mobileNumber);
//		if(customer.isPresent()) {
//			 Integer customer_id=customer.get().getCustomer_id();
//			 Optional<Account> account=accountRepository.findById(customer_id);
//			 return null;
//		}else {
//			return null;
//		}
//		
//		
//	}
	
	public Optional<CustomerAccountDetails> getAccountDetails(String mobileNumber) {
	    Optional<Customer> customer = customerRepository.findBymobileNumber(mobileNumber);
	    if (customer.isPresent()) {
	        Integer customerId = customer.get().getCustomer_id();
	        Optional<Account> account = accountRepository.findById(customerId);
	        if (account.isPresent()) {
	            return Optional.of(new CustomerAccountDetails(customer.get(), account.get()));
	        }
	    }
	    return Optional.empty();
	}
	
	public Boolean deleteCustomerAccount(String mobileNumber) {
	    Optional<Customer> customer = customerRepository.findBymobileNumber(mobileNumber);
	    if (customer.isPresent()) {
	        Integer customerId = customer.get().getCustomer_id();
	        Optional<Account> account = accountRepository.findById(customerId);
	        if (account.isPresent()) {
	        	customerRepository.deleteById(customerId);
	        	accountRepository.deleteById(customerId);
	        	return true;
	        }
	    }
	    return false;
	}
	
	
	public Optional<CustomerAccountDetails> updateCustomerAccount(Long AccountNumber,CustomerAccountDetails customerAccount) {
		Optional<Account> account = accountRepository.findByAccountNumber(AccountNumber);
		if(account.isPresent()) {
			Integer CustomerId=account.get().getCustomer_id();
			Optional<Customer> cust=customerRepository.findById(CustomerId);
			if(cust.isPresent()) {
				cust.get().setCustomer_id(CustomerId);
				cust.get().setName(customerAccount.getName());
				cust.get().setEmail(customerAccount.getEmail());
				cust.get().setMobileNumber(customerAccount.getMobileNumber());
				
				account.get().setCustomer_id(CustomerId);
				account.get().setAccount_type(customerAccount.getAccount().getAccount_type());
				account.get().setBranch_address(customerAccount.getAccount().getBranch_address());
				
				customerRepository.save(cust.get());
				accountRepository.save(account.get());
				return Optional.of(new CustomerAccountDetails(cust.get(), account.get()));
			}
		}
		return null;
	}
	
}
