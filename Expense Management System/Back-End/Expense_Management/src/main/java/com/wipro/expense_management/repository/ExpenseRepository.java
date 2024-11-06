package com.wipro.expense_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.expense_management.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("SELECT e FROM Expense e WHERE e.user.userId = :userId AND FUNCTION('DATE_FORMAT', e.date, '%Y-%m') = :period")
	List<Expense> findByUserAndPeriod(@Param("userId") Long userId, @Param("period") String period);


}
