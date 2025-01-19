package com.wipro.ms.entity;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	@Id
	private Integer customer_id;
	private Long accountNumber=1000000000L + new Random().nextInt(900000000);;
	private String account_type="Saving";
	private String branch_address="Near Dmart, Karve Nagar Pune";
	
}