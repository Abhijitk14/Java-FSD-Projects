package com.wipro.expense_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.expense_management.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
