package com.wipro.expense_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseManagementApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagementApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		System.out.println("Starting code...");
	}

}
