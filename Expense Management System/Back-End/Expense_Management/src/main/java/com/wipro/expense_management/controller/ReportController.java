package com.wipro.expense_management.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.expense_management.entity.Notification;
import com.wipro.expense_management.entity.Report;
import com.wipro.expense_management.services.NotificationService;
import com.wipro.expense_management.services.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user/reports")
public class ReportController {
	@Autowired
    private ReportService reportService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/generate")
    public ResponseEntity<Report> generateReport(@RequestBody Report reportRequest) {
        try {
            // Ensure the request body has all the required fields
            if (reportRequest.getUser() == null || reportRequest.getPeriod() == null || reportRequest.getTotalAmount() == null) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Now passing all necessary data to the service: userId, period, and totalAmount
            Report generatedReport = reportService.generateReport(
                reportRequest.getUser().getUserId(), 
                reportRequest.getPeriod(), 
                reportRequest.getTotalAmount()
            );
            
            // Create a notification for report generation
            Notification notification = new Notification();
            notification.setMessage("Report generated for period: " + reportRequest.getPeriod());
            notification.setStatus("Unread");
            notification.setDate(LocalDate.now());
            notificationService.createNotification(notification);

            return ResponseEntity.ok(generatedReport);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null);  // Handle 400 Bad Request errors properly
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);
        return report.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);

        // Create a notification for report deletion
        Notification notification = new Notification();
        notification.setMessage("Report with ID " + id + " was deleted.");
        notification.setStatus("Unread");
        notification.setDate(LocalDate.now());
        notificationService.createNotification(notification);

        return ResponseEntity.noContent().build();
    }
}
