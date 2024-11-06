package com.wipro.expense_management.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.expense_management.entity.Report;
import com.wipro.expense_management.entity.User;
import com.wipro.expense_management.repository.ReportRepository;
import com.wipro.expense_management.repository.UserRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;
    
    public Report generateReport(Long userId, String period, Double totalAmount) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        // Create the report
        Report report = new Report();
        report.setUser(user);
        report.setPeriod(period);
        report.setTotalAmount(totalAmount);
        report.setGeneratedDate(LocalDate.now());  // Set current date and time

        return reportRepository.save(report);
    }


    public Optional<Report> getReportById(Long reportId) {
        return reportRepository.findById(reportId);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }
}
