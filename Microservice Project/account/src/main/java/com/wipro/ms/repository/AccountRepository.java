package com.wipro.ms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ms.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Optional<Account> findByAccountNumber(Long account_number);
	
}
