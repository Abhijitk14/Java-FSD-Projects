package com.wipro.ms.dto;

import com.wipro.ms.entity.Account;
import com.wipro.ms.entity.Customer;

import lombok.Data;

@Data
public class CustomerAccountDetails {
    
	
	private String name;
	private String email;
	private String mobileNumber;
	
    private  Account account;

    public CustomerAccountDetails(Customer customer, Account account) {
       this.name=customer.getName();
       this.email=customer.getEmail();
       this.mobileNumber=customer.getMobileNumber();
       this.account = account;
    }

	@Override
	public String toString() {
		return "CustomerAccountDetails [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", account=" + account + "]";
	}
    
    
}

