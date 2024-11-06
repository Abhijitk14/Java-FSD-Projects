package com.wipro.expense_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.expense_management.entity.Expense;
import com.wipro.expense_management.exception.ResourceNotFoundException;
import com.wipro.expense_management.repository.CategoryRepository;
import com.wipro.expense_management.repository.ExpenseRepository;
import com.wipro.expense_management.repository.UserRepository;

@Service
public class ExpenseService {

 @Autowired
 private ExpenseRepository expenseRepository;

/**
 * getAllExpenses service method is used for get all expenses
 * @return
 */
 public List<Expense> getAllExpenses() {
     return expenseRepository.findAll();
 }

 /**
  * getExpenseById service method is used to get expense by id
  * @param id
  * @return
  */
 public Optional<Expense> getExpenseById(Long id) {
     return expenseRepository.findById(id);
 }

 /**
  * createExpense service method is used to store/create expense data
  * @param expense
  * @return
  */
 public Expense createExpense(Expense expense) {
   return expenseRepository.save(expense);
 }

 /**
  * updateExpense service method is used to update expense data
  * @param id
  * @param expense
  * @return
  */
 public Expense updateExpense(Long id, Expense expense) {
	 Optional<Expense> expense1=expenseRepository.findById(id);
	 if(expense1.isPresent()) {
		 expense.setExpenseId(id);
		 expenseRepository.save(expense);
		 return expense;
	 }else {
		 throw new ResourceNotFoundException("Expense Not Found");
	 }
 }

 /**
  * deleteExpense service method is used to delete expense object/data
  * @param id
  */
 public void deleteExpense(Long id) {
     expenseRepository.deleteById(id);
 }

}
