package com.wipro.expense_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.expense_management.entity.Notification;
import com.wipro.expense_management.exception.ResourceNotFoundException;
import com.wipro.expense_management.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
    	// Additional logic to set default status, etc., if needed
        if (notification.getStatus() == null) {
            notification.setStatus("Unread"); // Default status
        }
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications;
    }
    
    public Optional<Notification> getNotificationById(Long id) {
        Optional<Notification> notifications = notificationRepository.findById(id);
        return notifications;
    }

    public void deleteNotification(Long id) {
    	Optional<Notification> notifications = notificationRepository.findById(id);
    	if(notifications.isPresent()) {
    		notificationRepository.deleteById(id);
    	}else {
    		throw new ResourceNotFoundException("notification with id: "+id+" Not Found");
    	}
    }
    
    public Notification updateNotification(Long id, Notification notification) {
    	Optional<Notification> notifications = notificationRepository.findById(id);
    	if(notifications.isPresent()) {
    		notification.setNotificationId(id);
    		notification.setDate(notifications.get().getDate());
    		notification.setMessage(notifications.get().getMessage());
    		notification.setUser(notifications.get().getUser());
    		notificationRepository.save(notification);
    		return notification;
    	}else {
    		throw new ResourceNotFoundException("notification with id: "+id+" Not Found");
    	}
    }
}

