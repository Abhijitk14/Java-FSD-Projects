package com.wipro.expense_management.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.expense_management.entity.Notification;
import com.wipro.expense_management.entity.User;
import com.wipro.expense_management.services.NotificationService;
import com.wipro.expense_management.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private NotificationService notificationService;
    
    /**
     * Controller method to addUser 
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
    	userService.addUser(user);
    	Notification notification = new Notification();
        notification.setMessage("New user registered: " + user.getEmail());
        notification.setDate(LocalDate.now());
        notification.setStatus("Unread");
        notificationService.createNotification(notification);
        return ResponseEntity.ok("User registered successfully");
    }
    
}
