package com.wipro.expense_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.expense_management.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	
	 List<Report> findByUserUserId(Long userId);
}
