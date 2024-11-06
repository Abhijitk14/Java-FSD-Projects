package com.wipro.expense_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.expense_management.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	 List<Notification> findByUserUserId(Long userId);
}
