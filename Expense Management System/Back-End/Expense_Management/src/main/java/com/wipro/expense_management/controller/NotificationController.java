package com.wipro.expense_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.expense_management.entity.Notification;
import com.wipro.expense_management.services.NotificationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotification() {
        List<Notification> notifications = notificationService.getAllNotification();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notification>> getNotificationById(@PathVariable Long id) {
    	Optional<Notification> notifications = notificationService.getNotificationById(id);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        notificationService.updateNotification(id, notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

