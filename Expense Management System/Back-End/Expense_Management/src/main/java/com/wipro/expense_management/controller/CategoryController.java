package com.wipro.expense_management.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.expense_management.entity.Category;
import com.wipro.expense_management.entity.Notification;
import com.wipro.expense_management.services.CategoryService;
import com.wipro.expense_management.services.NotificationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user/categories")
public class CategoryController {

 @Autowired
 private CategoryService categoryService;
 
 @Autowired
 private NotificationService notificationService;

 @GetMapping
 public ResponseEntity<List<Category>> getAllCategories() {
     return ResponseEntity.ok(categoryService.getAllCategories());
 }

 @GetMapping("/{id}")
 public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id) {
     return ResponseEntity.ok(categoryService.getCategoryById(id));
 }

 @PostMapping
 public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	
	 Category savedCategory=categoryService.createCategory(category);
	// Create a notification for adding a new category
     Notification notification = new Notification();
     notification.setMessage("Category '" + savedCategory.getName() + "' added.");
     notification.setDate(LocalDate.now());
     notification.setStatus("Unread");
     notificationService.createNotification(notification);
     return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	 Category updateCategory=categoryService.updateCategory(id, category);
	// Create a notification for adding a new category
     Notification notification = new Notification();
     notification.setMessage("Category '" + updateCategory.getName() + "' Updated.");
     notification.setDate(LocalDate.now());
     notification.setStatus("Unread");
     notificationService.createNotification(notification);
     return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
     categoryService.deleteCategory(id);
  // Create a notification for adding a new category
     Notification notification = new Notification();
     notification.setMessage("Category with ID: "+id+" Deleted");
     notification.setDate(LocalDate.now());
     notification.setStatus("Unread");
     notificationService.createNotification(notification);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}

